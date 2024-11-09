package com.backend3K6_2024.backendG16.Pruebas.controller;

import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
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

    @PostMapping("/crearPrueba")
    public ResponseEntity<PruebaDTO> post(
            @RequestParam Integer interesadoId,
            @RequestParam Integer vehiculoId,
            @RequestParam Integer empleadoId){
        try {
            PruebaDTO pruebaDTO1 = pruebaService.create(interesadoId, vehiculoId, empleadoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(pruebaDTO1);
        } catch (BadRequestException e){
            return ResponseEntity.badRequest().header("ERROR_MSG", e.getMessage()).build();
        }
    }
}
