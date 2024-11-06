package com.backend3K6_2024.backendG16.Empleados.service;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Empleados.DTO.EmpleadoDTO;
import com.backend3K6_2024.backendG16.Empleados.mapper.EmpleadoMapper;
import com.backend3K6_2024.backendG16.Empleados.repository.EmpleadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // - MÉTODOS GET -
    public List<EmpleadoDTO> getAll() {
        List<Empleado> empleado = empleadoRepository.findAll();
        return empleado.stream()
                .map(EmpleadoMapper::toDTO)
                .toList();
    }
}