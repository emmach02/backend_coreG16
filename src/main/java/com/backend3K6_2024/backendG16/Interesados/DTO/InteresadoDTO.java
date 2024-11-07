package com.backend3K6_2024.backendG16.Interesados.DTO;

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

public class InteresadoDTO {
    private Integer id;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private String restringido;
    private String nroLicencia;
    private LocalDateTime fechaVencimientoLicencia;

}
