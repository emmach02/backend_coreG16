package com.backend3K6_2024.backendG16.Pruebas.DTO;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El ID del empleado es obligatorio")
    private Integer idEmpleado;
    @NotNull(message = "El ID del interesado es obligatorio")
    private Integer idInteresado;
    @NotNull(message = "El ID del Vehiculo es obligatorio")
    private Integer idVehiculo;
}
