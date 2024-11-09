package com.backend3K6_2024.backendG16.Posiciones.DTO;

import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString
public class PosicionDTO {
    private Integer idPosicion;
    private Vehiculo vehiculo;
    private LocalDateTime fechaHora;
    private Double latitud;
    private Double longitud;
}
