package com.backend3K6_2024.backendG16.Posiciones.controller;

import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.repository.PosicionRepository;
import com.backend3K6_2024.backendG16.Posiciones.service.PosicionService;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
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
@RequestMapping("/api/posiciones")

public class PosicionController {

    @Autowired
    private PosicionService posicionService;

    @GetMapping("")
    public ResponseEntity<List<PosicionDTO>> getAll() {
        List<PosicionDTO> posiciones = posicionService.getAll();
        return ResponseEntity.ok(posiciones);
    }
}
