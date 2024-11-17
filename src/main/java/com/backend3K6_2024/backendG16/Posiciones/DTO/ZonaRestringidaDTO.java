package com.backend3K6_2024.backendG16.Posiciones.DTO;

import com.backend3K6_2024.backendG16.Posiciones.entity.Coordenadas;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString
public class ZonaRestringidaDTO {
    private Coordenadas noroeste;
    private Coordenadas sureste;
}
