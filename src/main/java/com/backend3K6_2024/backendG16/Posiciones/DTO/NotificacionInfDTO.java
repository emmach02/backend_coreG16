package com.backend3K6_2024.backendG16.Posiciones.DTO;

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
public class NotificacionInfDTO {
    private Integer idPrueba;
    private LocalDateTime fechaEmision;
    private String texto;
    private String nroTelefono;
}
