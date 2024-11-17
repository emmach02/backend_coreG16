package com.backend3K6_2024.backendG16.Pruebas.service;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Empleados.repository.EmpleadoRepository;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Interesados.repository.InteresadoRepository;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.repository.PosicionRepository;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaRequestDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Pruebas.mapper.PruebaMapper;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private InteresadoRepository interesadoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private PosicionRepository posicionRepository;

    //-METODOS GET-
    public List<PruebaDTO> getAll(){
        List<Prueba> pruebas = pruebaRepository.findAll();
        return pruebas.stream()
                .map(PruebaMapper::toDTO)
                .toList();
    }

        //Get pruebas en curso
    //Resuelve el punto B. (por el momento, ver que significa "en un momento dado")
    public List<PruebaDTO> getPruebasEnCurso(){
        List<Prueba> pruebas = pruebaRepository.findByFechaHoraFinIsNull();
        return pruebas.stream()
                .map(PruebaMapper::toDTO)
                .toList();
    }

    //Get PRUEBA en curso para un vehículo
    public PruebaDTO getPruebaEnCurso(Integer idVehiculo){
        //Traemos el Vehículo al que estamos creando la posición
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo).get();
        //Buscamos la prueba de dicho vehiculo, siempre y cuando tenga fechaFin = null
        Prueba prueba = pruebaRepository.findByVehiculoAndFechaHoraFinIsNull(vehiculo);
        //Ahora terminamos de mapear la entidad a DTO
        return PruebaMapper.toDTO(prueba);
    }

    //-METODOS POST-
    //Resuelve el punto 1A
    @Transactional
    public PruebaDTO create(@RequestBody PruebaRequestDTO pruebaRequestDTO) throws BadRequestException {
        //Traemos los vehiculos y empleados correspondientes, si no arroja excepcion
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(pruebaRequestDTO.getIdVehiculo());
        Optional<Empleado> empleado = empleadoRepository.findById(pruebaRequestDTO.getIdEmpleado());
        if(empleado.isEmpty()) {
            throw new BadRequestException("El empleado no existe");
        }
        if(vehiculo.isEmpty()) {
            throw new BadRequestException("El vehiculo no existe");
        }

        //Verificado si existe el interesado, si existe verificamos licencia y si puede probar autos
        Interesado interesado = interesadoRepository.findById(pruebaRequestDTO.getIdInteresado()).get();
        if(interesado == null) {
            throw new BadRequestException("No existe el interesado");
        } else {
            if (interesado.getFechaVencimientoLicencia().isBefore(LocalDateTime.now())) {
                throw new BadRequestException("El interesado tiene la licencia vencida");
            }
            if (interesado.getRestringido()){
                throw new BadRequestException("Interesado restringido, no puede probar vehiculos");
            }
        }

        //Validamos el vehiculo si está disponible o no, para ello vemos TODAS las pruebas de cierto
        //vehículo y nos fijamos si es que tiene alguna con fechaFin = null, si no lo tiene entonces el mismo
        //tiene una prueba en curso
        List<Prueba> pruebasDelVehiculo = pruebaRepository.findPruebasByVehiculo(vehiculo.get());
        if (!pruebasDelVehiculo.isEmpty()) {
            for (Prueba prueba : pruebasDelVehiculo) {
                //La bbdd ahora acepta nulos, por lo que si no tiene fecha Fin indica que la prueba está en curso
                if ((prueba.getFechaHoraFin() == null)) {
                    throw new BadRequestException("El vehículo ya se encuentra en prueba");
                }
            }
        }

        //Creo la prueba

        LocalDateTime fechaActual = LocalDateTime.now();

        Prueba prueba = new Prueba();
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado.get());
        prueba.setVehiculo(vehiculo.get());
        prueba.setFechaHoraInicio(fechaActual);
        prueba.setInfraccion(false);
        pruebaRepository.save(prueba);

        //Le "seteo" la posición incial, es decir la prueba siempre arranca desde la agencia
        Posicion posicion = new Posicion();
        posicion.setFechaHora(fechaActual);
        posicion.setIdVehiculo(vehiculo.get().getId());
        //Posiciones de la agencia....
        posicion.setLatitud(42.50);
        posicion.setLongitud(1.53);
        posicionRepository.save(posicion);

        return PruebaMapper.toDTO(prueba);
    }

    // Métodos PUT
    //Resulve el punto 1C
    @Transactional
    public PruebaDTO finalizarPrueba(Integer pruebaId, String comentario) throws NotFoundException {
        Optional<Prueba> prueba = pruebaRepository.findById(pruebaId);
        if(prueba.isEmpty()) {
            throw new NotFoundException("No existe la prueba");
        }
        
        prueba.get().setFechaHoraFin(LocalDateTime.now());
        prueba.get().setComentarios(comentario);
        Prueba pruebaGuardada = pruebaRepository.save(prueba.get());
        return PruebaMapper.toDTO(pruebaGuardada);
    }

}
