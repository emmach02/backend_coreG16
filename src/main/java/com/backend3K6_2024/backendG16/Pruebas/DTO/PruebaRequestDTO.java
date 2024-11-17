package com.backend3K6_2024.backendG16.Pruebas.DTO;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class PruebaRequestDTO {
    private Integer idEmpleado;
    private Integer idInteresado;
    private Integer idVehiculo;
}
