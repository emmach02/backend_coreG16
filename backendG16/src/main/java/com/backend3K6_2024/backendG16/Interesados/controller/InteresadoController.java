package com.backend3K6_2024.backendG16.Interesados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Interesados.service.InteresadoService;

import java.util.List;

@RestController
@RequestMapping("/api/Interesados")
public class InteresadoController {

    @Autowired
    public InteresadoService interesadoService;

@GetMapping("")
public ResponseEntity<List<InteresadoDTO>> getAll() {
    List<InteresadoDTO> interesados = interesadoService.getAll();
    return ResponseEntity.ok(interesados);
    }

}
