package com.backend3K6_2024.backendG16.Marcas.DTO;


import jakarta.persistence.SecondaryTable;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.StandardException;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class MarcaDTO {
    Integer idMarca;
    String nombre;
}
