package com.backend3K6_2024.backendG16.Vehiculos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend3K6_2024.backendG16.Vehiculos.DTO.VehiculoDTO;
import com.backend3K6_2024.backendG16.Vehiculos.service.VehiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/Vehiculos")
public class VehiculoController {

    @Autowired
    public VehiculoService vehiculoService;

    @GetMapping("")
    public ResponseEntity<List<VehiculoDTO>> getAll() {
        List<VehiculoDTO> vehiculos = vehiculoService.getAll();
        return ResponseEntity.ok(vehiculos);
    }
}