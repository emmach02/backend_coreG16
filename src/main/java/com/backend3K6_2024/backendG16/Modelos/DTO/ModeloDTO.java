package com.backend3K6_2024.backendG16.Modelos.DTO;
import com.backend3K6_2024.backendG16.Marcas.entity.Marca;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class ModeloDTO {
    private Integer idModelo;
    private Marca marca;
    private String descripcion;
}
