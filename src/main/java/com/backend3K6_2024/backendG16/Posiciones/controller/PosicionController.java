package com.backend3K6_2024.backendG16.Posiciones.controller;

import com.backend3K6_2024.backendG16.Posiciones.DTO.FechasDTO;
import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.repository.PosicionRepository;
import com.backend3K6_2024.backendG16.Posiciones.service.PosicionService;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/api/posiciones")

public class PosicionController {

    @Autowired
    private PosicionService posicionService;

    //MÉTODOS GET

    //Todas las posiciones
    @GetMapping("")
    public ResponseEntity<List<PosicionDTO>> getAll() {
        List<PosicionDTO> posiciones = posicionService.getAll();
        return ResponseEntity.ok(posiciones);
    }

    //Todas las posiciones de UN vehículo en particular
    @GetMapping("/{id}")
    public ResponseEntity<List<PosicionDTO>> getById(@PathVariable Integer id) throws NotFoundException {
        List<PosicionDTO> posiciones = posicionService.getPosDeVehiculo(id);
        return ResponseEntity.ok(posiciones);
    }

    //Todas las posiciones de UN vehículo por fecha desde/hasta
    @GetMapping("entreFechas/{id}")
    public ResponseEntity<List<PosicionDTO>> getPosicionesEntreFechas(@PathVariable Integer id,
                                                                      @RequestBody FechasDTO fechasDTO)
            throws NotFoundException {
        List<PosicionDTO> posicionDTO = posicionService.getPosVehiculoPorFechas(id, fechasDTO);
        return ResponseEntity.ok(posicionDTO);
    }

    // Métodos POST de posición de vehículo
    @PostMapping("/crearPosicion")
    public ResponseEntity<String> crearPosicion(@RequestBody PosicionDTO posicionDTO) {
        try {
            //Crear-Recibir posición nueva del vehículo y por consiguiente, si es nueva
            //hay que verificar si comete infracción (siempre es la última después de todo)
            //Lo hago dentro del create
            ResponseEntity<String> posDTO = posicionService.create(posicionDTO);
            return ResponseEntity.ok(posDTO.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("ERROR_MSG", e.getMessage()).build();
        }
    }
}
