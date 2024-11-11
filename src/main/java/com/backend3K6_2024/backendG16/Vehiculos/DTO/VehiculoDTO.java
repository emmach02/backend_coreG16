package com.backend3K6_2024.backendG16.Vehiculos.DTO;

import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class VehiculoDTO {
    private Integer id;
    private String patente;
    private Modelo modelo;
    private List<Posicion> posiciones;
}