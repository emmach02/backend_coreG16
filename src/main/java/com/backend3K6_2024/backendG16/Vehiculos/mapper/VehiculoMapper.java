package com.backend3K6_2024.backendG16.Vehiculos.mapper;

import com.backend3K6_2024.backendG16.Vehiculos.DTO.VehiculoDTO;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;


public class VehiculoMapper {

    public static VehiculoDTO toDTO(Vehiculo vehiculo) {
        return VehiculoDTO.builder()
                .id(vehiculo.getId())
                .patente(vehiculo.getPatente())
                .modelo(vehiculo.getModelo())
                .posiciones(vehiculo.getPosiciones())
                .build();
    }
    public static Vehiculo toEntity(VehiculoDTO vehiculoDTO) {
        return Vehiculo.builder()
                .id(vehiculoDTO.getId())
                .patente(vehiculoDTO.getPatente())
                .modelo(vehiculoDTO.getModelo())
                .posiciones(vehiculoDTO.getPosiciones())
                .build();
    }
}
