package com.backend3K6_2024.backendG16.Pruebas.service;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Empleados.repository.EmpleadoRepository;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Interesados.repository.InteresadoRepository;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Pruebas.mapper.PruebaMapper;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
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

    //-METODOS GET-
    public List<PruebaDTO> getAll(){
        List<Prueba> pruebas = pruebaRepository.findAll();
        return pruebas.stream()
                .map(PruebaMapper::toDTO)
                .toList();
    }

    //-METODOS POST-
    public PruebaDTO create(Integer interesadoId, Integer vehiculoId, Integer empleadoId) throws BadRequestException {
        //Traemos los vehiculos y empleados correspondientes, si no arroja excepcion
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(vehiculoId);
        Optional<Empleado> empleado = empleadoRepository.findById(empleadoId);
        if(empleado.isEmpty()) {
            throw new BadRequestException("El empleado no existe");
        }
        if(vehiculo.isEmpty()) {
            throw new BadRequestException("El empleado no existe");
        }

        //Verificado si existe el interesado, si existe verificamos licencia y si puede probar autos
        Interesado interesado = interesadoRepository.findById(interesadoId).get();
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

        //Validamos el vehiculo si está disponible o no
        List<Prueba> pruebasDelVehiculo = pruebaRepository.findPruebasByVehiculo(vehiculo.get());
        if (!pruebasDelVehiculo.isEmpty()) {
            for (Prueba prueba : pruebasDelVehiculo) {
                //La bbdd ahora acepta nulos, por lo que si no tiene fecha Fin indica que la prueba está en curso
                if ((prueba.getFechaHoraFin() == null)) {
                    throw new BadRequestException("El vehículo ya se encuentra en prueba");
                }
            }
        }

        Prueba prueba = new Prueba();
        prueba.setInteresado(interesado);
        prueba.setEmpleado(empleado.get());
        prueba.setVehiculo(vehiculo.get());
        prueba.setFechaHoraInicio(LocalDateTime.now());
        pruebaRepository.save(prueba);
        return PruebaMapper.toDTO(prueba);

    }

}
