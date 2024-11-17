package com.backend3K6_2024.backendG16.Vehiculos.service;

import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import com.backend3K6_2024.backendG16.Vehiculos.DTO.VehiculoDTO;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.mapper.VehiculoMapper;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private PruebaRepository pruebaRepository;

    // - MÉTODOS GET -
    public List<VehiculoDTO> getAll() {
        List<Vehiculo> vehiculo = vehiculoRepository.findAll();
        return vehiculo.stream()
                .map(VehiculoMapper::toDTO)
                .toList();
    }

    //Función para verificar la existencia de un vehículo por ID
    public Boolean existeVehiculo(Integer idVehículo) {
        return vehiculoRepository.existsById(idVehículo);
    }
}