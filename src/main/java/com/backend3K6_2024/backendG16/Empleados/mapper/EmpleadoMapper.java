package com.backend3K6_2024.backendG16.Empleados.mapper;

import com.backend3K6_2024.backendG16.Empleados.DTO.EmpleadoDTO;
import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;

public class EmpleadoMapper {
    public static EmpleadoDTO toDTO(Empleado empleado){

        return EmpleadoDTO.builder()
                .legajo(empleado.getLegajo())
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .build();
    }

    public static Empleado toEntity(EmpleadoDTO empleadoDTO){

        return Empleado.builder()
                .legajo(empleadoDTO.getLegajo())
                .nombre(empleadoDTO.getNombre())
                .apellido(empleadoDTO.getApellido())
                .build();
    }
}
