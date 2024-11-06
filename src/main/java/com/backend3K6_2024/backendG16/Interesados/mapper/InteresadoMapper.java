package com.backend3K6_2024.backendG16.Interesados.mapper;

import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;

public class InteresadoMapper {
    public static InteresadoDTO toDTO(Interesado interesado){

        return InteresadoDTO.builder()
                .id(interesado.getId())
                .tipo_documento(interesado.getTipo_documento())
                .nombre(interesado.getNombre())
                .apellido(interesado.getApellido())
                .restringido(interesado.getRestringido())
                .nro_licencia(interesado.getNro_licencia())
                .fecha_vencimiento_licencia(interesado.getFecha_vencimiento_licencia())
                .build;
    }
    public static Intersado toEntity(InteresadoDTO interesadoDTO){

        return Interesado.builder()
                .id(interesadoDTO.getId())
                .tipo_documento(interesadoDTO.getTipo_documento())
                .nombre(interesadoDTO.getNombre())
                .apellido(interesadoDTO.getApellido())
                .restringido(interesadoDTO.getRestringido())
                .nro_licencia(interesadoDTO.getNro_licencia())
                .fecha_vencimiento_licencia(interesadoDTO.getFecha_vencimiento_licencia())
                .build;
    }
}
