package com.backend3K6_2024.backendG16.Pruebas.controller;

import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.ComentarioDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaRequestDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
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

    //Para ver todas las pruebas
    @GetMapping("")
    public ResponseEntity<List<PruebaDTO>> getAll() {
        List<PruebaDTO> pruebas = pruebaService.getAll();
        return ResponseEntity.ok(pruebas);
    }

    //Resuelve b. Listar todas las pruebas en curso en un momento dado
    @GetMapping("/en-curso")
    public ResponseEntity<List<PruebaDTO>> getEnCurso() {
        List<PruebaDTO> pruebas = pruebaService.getPruebasEnCurso();
        return ResponseEntity.ok(pruebas);
    }

    //Resuelve = a. Crear una nueva prueba, validando que el cliente no tenga la licencia vencida
    //ni que esté restringido para probar vehículos en la agencia. Vamos a asumir
    //que un interesado puede tener una única licencia registrada en el sistema y
    //que todos los vehículos están patentados. También deben realizarse los
    //controles razonables del caso; por ejemplo, que un mismo vehículo no esté
    //siendo probado en ese mismo momento.

    @PostMapping("/crearPrueba")
    public ResponseEntity<PruebaDTO> post(
            //TODO a requestBody
            @RequestBody PruebaRequestDTO pruebaRequestDTO) {
        return new ResponseEntity<>(pruebaService.create(pruebaRequestDTO), HttpStatus.CREATED);

        /*try {
            PruebaDTO pruebaDTO1 = pruebaService.create(pruebaRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(pruebaDTO1);
        } catch (BadRequestException e){
            return ResponseEntity.badRequest().header("ERROR_MSG", e.getMessage()).build();
        }*/
    }

    //Resuelve el punto c= Finalizar una prueba, permitiéndole al empleado agregar un comentario
    //sobre la misma.
    @PatchMapping("finalizar/{pruebaId}")
    public ResponseEntity<PruebaDTO> finalizarPrueba(
            @PathVariable Integer pruebaId, @RequestBody ComentarioDTO comentarioDTO) throws NotFoundException, BadRequestException {
        String comentario = comentarioDTO.getComentario();
        PruebaDTO pruebaFinalizada = pruebaService.finalizarPrueba(pruebaId, comentario);
        return ResponseEntity.ok(pruebaFinalizada);
    }
}
