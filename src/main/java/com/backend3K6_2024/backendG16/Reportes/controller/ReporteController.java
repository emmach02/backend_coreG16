package com.backend3K6_2024.backendG16.Reportes.controller;

import com.backend3K6_2024.backendG16.Posiciones.DTO.FechasDTO;
import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.service.PruebaService;
import com.backend3K6_2024.backendG16.Reportes.service.ReporteService;
import com.backend3K6_2024.backendG16.exceptions.BadRequestException;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reportes")

public class ReporteController {

    @Autowired
    private final ReporteService reporteService;

    //Es autowired o de esta manera, una de dos.
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    //Reporte I - Creacion de un reporte con las pruebas que presentaron incidentes
    @GetMapping("/incidentes")
    public ResponseEntity<List<PruebaDTO>> pruebasConIncidente() {
        List<PruebaDTO> incidentes = reporteService.getPruebasConIncidente();
        if (incidentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incidentes);
        }
    }

    //Endpoint del Reporte II - Reporte con incidentes de un empleado
    @GetMapping("/incidentes-empleado/{idEmpleado}")
    public ResponseEntity<List<PruebaDTO>> pruebasConIncidenteEmpleado(
            @PathVariable Integer idEmpleado) throws NotFoundException {
        List<PruebaDTO> incidentesEmp = reporteService.getIncidentesDeEmp(idEmpleado);
        if (incidentesEmp.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incidentesEmp);
        }
    }

    //Endpoint del Reporte IV - Reporte de pruebas para un vehículo particular
    @GetMapping("pruebas-vehiculo/{idVehiculo}")
    public ResponseEntity<List<PruebaDTO>> pruebasDeVehiculo(
            @PathVariable Integer idVehiculo) throws NotFoundException {
        List<PruebaDTO> pruebasVehiculo = reporteService.getPruebasDeVehiculo(idVehiculo).getBody();
        if (pruebasVehiculo.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pruebasVehiculo);
        }
    }

    //Enpoint de Reporte III - Km de prueba de un vehículo particular
    @GetMapping("kilometros-vehiculo/{idVehiculo}")
    public ResponseEntity<String> kilometroVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestBody FechasDTO fechasDTO) throws NotFoundException, BadRequestException {
        return reporteService.calcularKmPruebas(idVehiculo, fechasDTO);
    }
}
