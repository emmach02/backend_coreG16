package com.backend3K6_2024.backendG16.Empleados.controller;

import com.backend3K6_2024.backendG16.Empleados.DTO.EmpleadoDTO;
import com.backend3K6_2024.backendG16.Empleados.repository.EmpleadoRepository;
import com.backend3K6_2024.backendG16.Empleados.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("api/empleados")
public class EmpleadoController {

    @Autowired
    public EmpleadoService empleadoService;

    @GetMapping("")
    public ResponseEntity<List<EmpleadoDTO>> getALl() {
        List<EmpleadoDTO> empleados = empleadoService.getAll();
        return ResponseEntity.ok(empleados);
    }
}

