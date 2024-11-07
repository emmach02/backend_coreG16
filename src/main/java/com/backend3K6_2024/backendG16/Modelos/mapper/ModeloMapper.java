package com.backend3K6_2024.backendG16.Modelos.mapper;

import com.backend3K6_2024.backendG16.Modelos.DTO.ModeloDTO;
import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;

public class ModeloMapper {

    public static ModeloDTO toDTO(Modelo modelo) {
        return ModeloDTO.builder()
                .idModelo(modelo.getIdModelo())
                .marca(modelo.getMarca())
                .descripcion(modelo.getDescripcion())
                .build();
    }

    public static Modelo toEntity(ModeloDTO modeloDTO) {

        return Modelo.builder()
                .idModelo(modeloDTO.getIdModelo())
                .marca(modeloDTO.getMarca())
                .descripcion(modeloDTO.getDescripcion())
                .build();
    }
}
