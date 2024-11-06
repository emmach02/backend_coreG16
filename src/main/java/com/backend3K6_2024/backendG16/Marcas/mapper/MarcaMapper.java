package com.backend3K6_2024.backendG16.Marcas.mapper;

import com.backend3K6_2024.backendG16.Marcas.DTO.MarcaDTO;
import com.backend3K6_2024.backendG16.Marcas.entity.Marca;

public class MarcaMapper {

    public static MarcaDTO toDTO(Marca marca){

        return MarcaDTO.builder()
                .idMarca(marca.getIdMarca())
                .nombre(marca.getNombre())
                .build();
    }

    public static Marca toEntity(MarcaDTO marcaDTO){

        return Marca.builder()
                .idMarca(marcaDTO.getIdMarca())
                .nombre(marcaDTO.getNombre())
                .build();
    }
}
