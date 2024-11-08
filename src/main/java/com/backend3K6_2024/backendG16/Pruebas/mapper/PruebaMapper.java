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

    public static Prueba toEntity(PruebaDTO pruebaDTO) {
    return Prueba.builder()
            .idPrueba(pruebaDTO.getIdPrueba())
            .vehiculo(pruebaDTO.getVehiculo())
            .interesado(pruebaDTO.getInteresado())
            .empleado(pruebaDTO.getEmpleado())
            .fechaHoraInicio(pruebaDTO.getFechaHoraInicio())
            .fechaHoraFin(pruebaDTO.getFechaHoraFin())
            .comentarios(pruebaDTO.getComentarios())
            .build();
    }
}
