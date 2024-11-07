package com.backend3K6_2024.backendG16.Interesados.mapper;

import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;

public class InteresadoMapper {
    public static InteresadoDTO toDTO(Interesado interesado){

        return InteresadoDTO.builder()
                .id(interesado.getId())
                .tipoDocumento(interesado.getTipo_documento())
                .nombre(interesado.getNombre())
                .apellido(interesado.getApellido())
                .restringido(interesado.getRestringido())
                .nroLicencia(interesado.getNro_licencia())
                .fechaVencimientoLicencia(interesado.getFecha_vencimiento_licencia())
                .build;
    }
    public static Intersado toEntity(InteresadoDTO interesadoDTO){

        return Interesado.builder()
                .id(interesadoDTO.getId())
                .tipoDocumento(interesadoDTO.getTipo_documento())
                .nombre(interesadoDTO.getNombre())
                .apellido(interesadoDTO.getApellido())
                .restringido(interesadoDTO.getRestringido())
                .nroLicencia(interesadoDTO.getNro_licencia())
                .fechaVencimientoLicencia(interesadoDTO.getFecha_vencimiento_licencia())
                .build;
    }
}
