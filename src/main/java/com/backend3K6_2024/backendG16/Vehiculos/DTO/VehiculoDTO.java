package com.backend3K6_2024.backendG16.Vehiculos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VehiculoDTO {
    private long id;
    private String patente;
    private long idMarca;


}