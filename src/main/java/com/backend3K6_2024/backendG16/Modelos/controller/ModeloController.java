package com.backend3K6_2024.backendG16.Modelos.controller;

import com.backend3K6_2024.backendG16.Marcas.DTO.MarcaDTO;
import com.backend3K6_2024.backendG16.Modelos.DTO.ModeloDTO;
import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Modelos.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api/modelos")


public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    // Metodos get //
    @GetMapping("")
    public ResponseEntity<List<ModeloDTO>> getAllModelos() {
        List<ModeloDTO> modelos = modeloService.getAll();
        return ResponseEntity.ok(modelos);
    }
}
