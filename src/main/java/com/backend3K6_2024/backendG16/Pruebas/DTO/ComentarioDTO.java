package com.backend3K6_2024.backendG16.Pruebas.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ComentarioDTO {
    @NotNull(message = "El comentario no puede ser nulo")
    private String comentario;
}
