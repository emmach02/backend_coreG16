package com.backend3K6_2024.backendG16.Vehiculos.DTO;

import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class VehiculoDTO {
    private long id;
    private String patente;
    private Modelo modelo;


}