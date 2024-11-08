package com.backend3K6_2024.backendG16.Pruebas.mapper;

import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;

public class PruebaMapper {

    public static PruebaDTO toDTO(Prueba prueba) {
        return PruebaDTO.builder()
                .idPrueba(prueba.getIdPrueba())
                .vehiculo(prueba.getVehiculo())
                .interesado(prueba.getInteresado())
                .empleado(prueba.getEmpleado())
                .fechaHoraInicio(prueba.getFechaHoraInicio())
                .fechaHoraFin(prueba.getFechaHoraFin())
                .comentarios(prueba.getComentarios())
                .build();
    }

    public static Prueba toEntity(PruebaDTO dto) {
    return Prueba.builder()
            .idPrueba(dto.getIdPrueba())
            .vehiculo(dto.getVehiculo())
            .interesado(dto.getInteresado())
            .empleado(dto.getEmpleado())
            .fechaHoraInicio(dto.getFechaHoraInicio())
            .fechaHoraFin(dto.getFechaHoraFin())
            .comentarios(dto.getComentarios())
            .build();
    }
}
