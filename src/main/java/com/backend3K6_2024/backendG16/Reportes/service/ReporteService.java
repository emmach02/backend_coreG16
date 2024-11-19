package com.backend3K6_2024.backendG16.Reportes.service;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Empleados.repository.EmpleadoRepository;
import com.backend3K6_2024.backendG16.Posiciones.DTO.FechasDTO;
import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Coordenadas;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.service.PosicionService;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.mapper.PruebaMapper;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    private final PruebaRepository pruebaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final PosicionService posicionService;

    public ReporteService(PruebaRepository pruebaRepository, EmpleadoRepository empleadoRepository, VehiculoRepository vehiculoRepository, PosicionService posicionService) {
        this.pruebaRepository = pruebaRepository;
        this.empleadoRepository = empleadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.posicionService = posicionService;
    }

    @Value("${reportes_path}")
    private String reportesPath;

    //Métodos para reportes

        //Reporte I - Pruebas con incidente
    public List<PruebaDTO> getPruebasConIncidente() {
        //Traemos todas las pruebas con incidentes de la BBDD
        List<Prueba> pruebasInfraccion = pruebaRepository.findByInfraccionTrue();

        //Estructura para la creación del reporte
        String encabezado = "Pruebas resultantes con Incidente";
        String nombreArch = "reporte_incidentes.txt";

        crearReporteTxt(pruebasInfraccion, encabezado, nombreArch);

        return pruebasInfraccion.stream()
                .map(PruebaMapper::toDTO)
                .toList();
    }

        //Reporte II - Pruebas con incidente para un empleado
    public List<PruebaDTO> getIncidentesDeEmp(Integer idEmpleado) throws NotFoundException {
        //Primero verificamos que exista el ID del empleado que ingresamos en el request
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(idEmpleado);
        if (empleadoOpt.isPresent()) {
            //Traemos todas las pruebas para con incidentes
            List<Prueba> pruebasInfraccion = pruebaRepository.findByInfraccionTrue();
            //Creamos una array de Incidentes para dicho empleado vacío
            List<Prueba> IncidentesDeEmp = new ArrayList<>();

            for (Prueba prueba : pruebasInfraccion) {
                //Acá recorremos todos los incidentes y en caso de coincidir los legajos, lo agregamos a la lista
                if(prueba.getEmpleado().getLegajo().equals(idEmpleado)) {
                    IncidentesDeEmp.add(prueba);
                }
            }

            //Estructura para la creación del reporte
            String datosEmpleado = empleadoOpt.get().getNombre() + " " + empleadoOpt.get().getApellido();
            String encabezado = "Pruebas resultantes con Incidente del empleado " +  datosEmpleado;
            String nombreArch = "reporte_incidentesDeEmpleado" + empleadoOpt.get().getLegajo()+ ".txt";

            crearReporteTxt(IncidentesDeEmp, encabezado, nombreArch);

            //Retornamos la lista DTO con el mapper
            return IncidentesDeEmp.stream()
                    .map(PruebaMapper::toDTO)
                    .toList();
        } else {
            //Si no existe el legajo se lanza excepcion
            throw new NotFoundException("Legajo de empleado no existe");
        }
    }

        //Reporte IV
    public List<PruebaDTO> getPruebasDeVehiculo(Integer idVehiculo) throws NotFoundException {
        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(idVehiculo);
        if (vehiculoOpt.isPresent()) {
            //Traemos todas las pruebas para el vehiculo en particular
            List<Prueba> pruebasVehiculo = pruebaRepository.findPruebasByVehiculo(vehiculoOpt.get());

            //Estructura para la creación del reporte
            String encabezado = "Pruebas ejecutadas para el vehículo id: " +  idVehiculo;
            String nombreArch = "reporte_pruebasVehiculo" + idVehiculo + ".txt";
            crearReporteTxt(pruebasVehiculo, encabezado, nombreArch);

            //Retornamos los objetos
            return pruebasVehiculo.stream()
                    .map(PruebaMapper::toDTO)
                    .toList();
        } else {
            throw new NotFoundException("Vehiculo no existe");
        }
    }

        //Reporte 3
    public ResponseEntity<String> calcularKmPruebas(
            @RequestParam Integer idVehiculo,
            @RequestBody FechasDTO fechasDTO) throws NotFoundException {
        //Traemos posiciones del vehiculo por fechas traidas en el body (json con fechas)
        List<PosicionDTO> posicionesVehiculo = posicionService.getPosVehiculoPorFechas(idVehiculo, fechasDTO);
        //Iteramos las posiciones de la lista, calculando la distancia del "punto" anterior con su siguiente hasta
        //completar la lista
        Double distanciaPrueba = 0.0;
        for (int i = 1; i < posicionesVehiculo.size(); i++) {
            //Posicion anterior
            PosicionDTO pos1 = posicionesVehiculo.get( i - 1);
            //Posicion Actual
            PosicionDTO pos2 = posicionesVehiculo.get(1);
            //Cálculo de la diferencia de coordenadas
                Coordenadas coorPos2 = new Coordenadas();
                coorPos2.setLat(pos2.getLatitud());
                coorPos2.setLon(pos2.getLongitud());
            distanciaPrueba += posicionService.calcularDistancia(pos1,coorPos2);
        }

        DateTimeFormatter parseDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (FileWriter arch = new FileWriter(reportesPath+"/distanciaKmVehiculo"+idVehiculo+".txt")) {
            arch.write("El vehículo" + idVehiculo + "ha recorrido" + distanciaPrueba + "km de prueba" + "\n" +
                    "durante la fecha " + fechasDTO.getDesde().format(parseDate) + " hasta la fecha " +
                    fechasDTO.getHasta().format(parseDate) + "\n");
        } catch (IOException e ){
            e.printStackTrace();
        }

        return ResponseEntity.ok("El vehículo "+idVehiculo+" ha recorrido "+distanciaPrueba+ "km de prueba" + "\n" +
                "durante la fecha " + fechasDTO.getDesde().format(parseDate) + " hasta la fecha " +
                fechasDTO.getHasta().format(parseDate) + "\n");
    }

    //Funciones Extras para reportes

    public void crearReporteTxt(List<Prueba> list, String encabezado, String nombreTxt) {
        try (FileWriter arch = new FileWriter(reportesPath+"/"+nombreTxt)) {
            arch.write(encabezado);
            arch.write("\n");
            DateTimeFormatter parseDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            for (Prueba prueba : list) {
                String datosEmpleado = prueba.getEmpleado().getNombre() + " " + prueba.getEmpleado().getApellido();
                String datosInteresado = prueba.getInteresado().getNombre() + " " + prueba.getInteresado().getApellido();
                String datosVehiculo = prueba.getVehiculo().getModelo().getMarca().getNombre() + " " +
                        prueba.getVehiculo().getModelo().getDescripcion();

                String fechaFin;
                if (prueba.getFechaHoraFin() != null) {
                    fechaFin = prueba.getFechaHoraFin().format(parseDate);
                } else {
                    fechaFin = "Prueba en curso en este momento";
                }

                String comentarios;
                if (prueba.getComentarios() != null) {
                    comentarios = prueba.getComentarios();
                } else {
                    comentarios = "Sin carga por el momento";
                }
                arch.write("ID Prueba: " + prueba.getIdPrueba() + "\n" +
                        "Fecha Incio: " + prueba.getFechaHoraInicio().format(parseDate) + "\n" +
                        "Fecha Fin: " + fechaFin + "\n" +
                        "Empleado: " + datosEmpleado + "\n" +
                        "Interesado: " + datosInteresado + "\n" +
                        "Comentario: " + comentarios + "\n" +
                        "Vehículo: " + datosVehiculo + " " + "Patente: " + prueba.getVehiculo().getPatente() + "\n" +
                        "----------------------------------------- \n");
            }
            System.out.println("Reporte impreso con éxito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


