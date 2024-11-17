package com.backend3K6_2024.backendG16.Posiciones.mapper;

import com.backend3K6_2024.backendG16.Modelos.DTO.ModeloDTO;
import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;

public class PosicionMapper {

    public static PosicionDTO toDTO(Posicion posicion) {
        return PosicionDTO.builder()
                .idPosicion(posicion.getIdPosicion())
                .idVehiculo(posicion.getIdVehiculo())
                .fechaHora(posicion.getFechaHora())
                .latitud(posicion.getLatitud())
                .longitud(posicion.getLongitud())
                .build();
    }

    public static Posicion toEntity(PosicionDTO posicionDTO) {

        return Posicion.builder()
                .idPosicion(posicionDTO.getIdPosicion())
                .idVehiculo(posicionDTO.getIdVehiculo())
                .fechaHora(posicionDTO.getFechaHora())
                .latitud(posicionDTO.getLatitud())
                .longitud(posicionDTO.getLongitud())
                .build();
    }

}
