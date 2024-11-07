package com.backend3K6_2024.backendG16.Interesados.mapper;

import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;

public class InteresadoMapper {

    public static InteresadoDTO toDTO(Interesado interesado){

        return InteresadoDTO.builder()
                .id(interesado.getId())
                .tipoDocumento(interesado.getTipoDocumento())
                .nombre(interesado.getNombre())
                .apellido(interesado.getApellido())
                .restringido(interesado.getRestringido())
                .nroLicencia(interesado.getNroLicencia())
                .fechaVencimientoLicencia(interesado.getFechaVencimientoLicencia())
                .build();
    }

    public static Interesado toEntity(InteresadoDTO interesadoDTO){

        return Interesado.builder()
                .id(interesadoDTO.getId())
                .tipoDocumento(interesadoDTO.getTipoDocumento())
                .nombre(interesadoDTO.getNombre())
                .apellido(interesadoDTO.getApellido())
                .restringido(interesadoDTO.getRestringido())
                .nroLicencia(interesadoDTO.getNroLicencia())
                .fechaVencimientoLicencia(interesadoDTO.getFechaVencimientoLicencia())
                .build();
    }
}
