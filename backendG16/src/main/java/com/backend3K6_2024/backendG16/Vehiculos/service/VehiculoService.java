package com.backend3K6_2024.backendG16.Vehiculos.service;

import com.backend3K6_2024.backendG16.Vehiculos.DTO.VehiculoDTO;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import com.backend3K6_2024.backendG16.Vehiculos.mapper.VehiculoMapper;
import com.backend3K6_2024.backendG16.Vehiculos.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    // - MÉTODOS GET -
    public List<VehiculoDTO> getAll() {
        List<Vehiculo> vehiculo = vehiculoRepository.findAll();
        return vehiculo.stream()
                .map(VehiculoMapper::toDTO)
                .toList();
    }
}