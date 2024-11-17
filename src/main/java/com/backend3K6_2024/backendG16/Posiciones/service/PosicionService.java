package com.backend3K6_2024.backendG16.Posiciones.service;

import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Interesados.repository.InteresadoRepository;
import com.backend3K6_2024.backendG16.Posiciones.DTO.FechasDTO;
import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionesApiDTO;
import com.backend3K6_2024.backendG16.Posiciones.DTO.ZonaRestringidaDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Coordenadas;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.mapper.PosicionMapper;
import com.backend3K6_2024.backendG16.Posiciones.repository.PosicionRepository;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.mapper.PruebaMapper;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import com.backend3K6_2024.backendG16.Vehiculos.service.VehiculoService;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service

public class PosicionService {

    @Autowired
    private PosicionRepository posicionRepository;
    @Autowired
    private VehiculoService vehiculoService;

    private final RestTemplate restTemplate;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private PruebaService pruebaService;
    @Autowired
    private InteresadoRepository interesadoRepository;

    @Autowired
    public PosicionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api_posiciones_url}")
    private String apiPosicionesUrl;

    //Métodos GET

    //Obtener todas las posiciones de la BBDD
    public List<PosicionDTO> getAll(){
        List<Posicion> posicions = posicionRepository.findAll();
        return posicions.stream()
                .map(PosicionMapper::toDTO)
                .toList();
    }

    //Obtener las posiciones de un vehículo
    public List<PosicionDTO> getPosDeVehiculo(Integer idVehiculo) throws NotFoundException {
        if (vehiculoService.existeVehiculo(idVehiculo)) {
            List<Posicion> posVehiculo = posicionRepository.findAllByIdVehiculo(idVehiculo);
            return posVehiculo.stream()
                    .map(PosicionMapper::toDTO)
                    .toList();
        } else {
            throw new NotFoundException("No existe vehículo");
        }
    }

    //Obtener las posiciones de un vehículo en un rango de fechas ENTRE HASTA
    public List<PosicionDTO> getPosVehiculoPorFechas(
            @PathVariable Integer idVehiculo,
            @RequestBody FechasDTO fechasDTO) throws NotFoundException {
        List<PosicionDTO> posVehiculo = getPosDeVehiculo(idVehiculo);
        List<PosicionDTO> posDesdeHasta = new ArrayList<>();
        for (PosicionDTO posDTO : posVehiculo) {
            if (entreFechas(posDTO.getFechaHora(), fechasDTO.getDesde(), fechasDTO.getHasta())) {
                posDesdeHasta.add(posDTO);
            }
        }
        return posDesdeHasta;
    }

    //Obtener la posición actual de un vehículo
    public PosicionDTO getUltimaPosicion(Integer idVehiculo) throws NotFoundException {
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo).get();
        if (vehiculo == null) {
            throw new NotFoundException("No existe el vehículo");
        } else {
            List<Posicion> posiciones = vehiculo.getPosiciones();
            //Nos aseguramos que la última sea la más reciente
            posiciones.sort(Comparator.comparing(Posicion::getFechaHora));
            //Retornamos mapeando la entidad a DTO
            return PosicionMapper.toDTO(posiciones.getLast());
        }
    }

    //Métodos POST para posiciones
    public ResponseEntity<String> create(@RequestBody PosicionDTO posicionDTO) throws BadRequestException {
        //Verificar que el vehículo para el cual creamos una posición, exista.
        if (!vehiculoService.existeVehiculo(posicionDTO.getIdVehiculo())) {
            throw new BadRequestException("El vehículo no existe");
        }

        //Creación de la posición con la hora actual
        posicionDTO.setFechaHora(LocalDateTime.now());
        Posicion posicion = PosicionMapper.toEntity(posicionDTO);
        posicionRepository.save(posicion);

        //Verifico si hubo infracción
        if (verificarInfraccion(posicionDTO)) {
            PruebaDTO pruebaDTO = pruebaService.getPruebaEnCurso(posicionDTO.getIdVehiculo());
            Interesado interesado = pruebaDTO.getInteresado();
            if (pruebaDTO == null) {
                throw new BadRequestException("No existe el prueba en curso");
            } else {
                //si tiene prueba en curso, la marcamos como en infraccion
                pruebaDTO.setInfraccion(true);
                pruebaRepository.save(PruebaMapper.toEntity(pruebaDTO));
                //marcar al interesado como restringido
                interesado.setRestringido(true);
                interesadoRepository.save(interesado);
                //TODO ENVIAR NOTIFICACION AL EMPLEADO QUE DICHO INTERESADO TUVO UN INCIDENTE
            }
        }

        return ResponseEntity.ok("OK");
    }

        //Funciones extras

    //Servicio para verificar si el vehículo está o no en una posición restringida
    private Boolean verificarInfraccion(PosicionDTO posicionDTO) {
        //Consumimos al api para traer el Objeto con posiciones de agencia, radio máximo y
        //coordenadas de zonas restringidas.
        PosicionesApiDTO posApi = consumirApi();
        System.out.println(posApi);

        //Distancia Respecto de la Agencia
        Double distanciaResAgencia = calcularDistancia(posicionDTO, posApi.getCoordenadasAgencia());
        //Mientras NO haya salido del radio y NO haya entrado en una zona
        //restringida, NO está en infracción.
        if (distanciaResAgencia > posApi.getRadioAdmitidoKm().doubleValue()
                || dentroZonaRest(posicionDTO, posApi.getZonasRestringidas())) {
            return true;
        }
        return false;
    }

    public Boolean entreFechas(LocalDateTime comparacion, LocalDateTime desde, LocalDateTime hasta) {
        if (comparacion.isAfter(desde) && comparacion.isBefore(hasta)) {
            return true;
        } else {
            return false;
        }
    }

    private PosicionesApiDTO consumirApi() {
        ResponseEntity<PosicionesApiDTO> response = restTemplate.exchange(
                apiPosicionesUrl, HttpMethod.GET, null, PosicionesApiDTO.class
        );

        if (response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        } else {
            throw new RuntimeException("Error al consumir la API " + response.getStatusCode());
        }
    }

    private Double calcularDistancia(PosicionDTO posicionDTO, Coordenadas coordenadas) {
        double radioTierra = 6371; // Radio de la Tierra en kilómetros
        double dLat = Math.toRadians(posicionDTO.getLatitud() - coordenadas.getLat());
        double dLon = Math.toRadians(posicionDTO.getLongitud() - coordenadas.getLon());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(coordenadas.getLat())) * Math.cos(Math.toRadians(posicionDTO.getLatitud())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;
    }

    private Boolean dentroZonaRest(PosicionDTO posicionDTO, List<ZonaRestringidaDTO> listaZonasRestringidas) {

        //Latitud y longitud de la posicion
        Double posLatitud = posicionDTO.getLatitud();
        Double posLongitud = posicionDTO.getLongitud();

        for (ZonaRestringidaDTO zonaRestringidaDTO : listaZonasRestringidas) {

            //Punto Noroeste y Punto Sureste de la zona
            Coordenadas noroeste = zonaRestringidaDTO.getNoroeste();
            Coordenadas sureste = zonaRestringidaDTO.getSureste();

            if (posLatitud <= noroeste.getLat() &&
                posLongitud >= noroeste.getLon() &&
                posLongitud <= sureste.getLon() &&
                    posLatitud >= sureste.getLat()) {
                return true;
            }
        }
        return false;
    }
}
