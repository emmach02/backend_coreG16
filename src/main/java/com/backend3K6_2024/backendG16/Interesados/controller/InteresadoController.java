package com.backend3K6_2024.backendG16.Interesados.entities.Controller;

import com.backend3K6_2024.backendG16.Interesados.service.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.*;

import java.util.List;

@RestController
@RequestMapping("/api/Interesados")
public class InteresadoController {
    private final InteresadoService interesadoService;

    @Autowired
    public InteresadoService interesadoService;


@GetMapping("")
public ResponseEntity<List<InteresadoDTO>> getAll() {
    List<InteresadoDTO> interesados = interesadoService.getAll();
    return ResponseEntity.ok(interesados);
}

}
