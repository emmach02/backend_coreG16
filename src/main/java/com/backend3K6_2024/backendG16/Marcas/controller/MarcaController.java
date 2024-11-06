package com.backend3K6_2024.backendG16.Marcas.controller;


import com.backend3K6_2024.backendG16.Marcas.DTO.MarcaDTO;
import com.backend3K6_2024.backendG16.Marcas.repository.MarcaRepository;
import com.backend3K6_2024.backendG16.Marcas.service.MarcaService;
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
@RequestMapping("/api/marcas")

public class MarcaController {

    @Autowired
    public MarcaService marcaService;

    //Métodos Get
    @GetMapping("")
    public ResponseEntity<List<MarcaDTO>> getAll() {
        List<MarcaDTO> marcas = marcaService.getAll();
        return ResponseEntity.ok(marcas);
    }
}
