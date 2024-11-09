package com.backend3K6_2024.backendG16.Pruebas.controller;

import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api/pruebas")
public class PruebaController {

    @Autowired
    public PruebaService pruebaService;

    @GetMapping("")
    public ResponseEntity<List<PruebaDTO>> getAll() {
        List<PruebaDTO> pruebas = pruebaService.getAll();
        return ResponseEntity.ok(pruebas);
    }

    @PostMapping("")
    public ResponseEntity<PruebaDTO> createPrueba(@RequestBody PruebaDTO pruebaDTO) {
        PruebaDTO savedPrueba = pruebaService.addPrueba(pruebaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrueba);
    }
}
