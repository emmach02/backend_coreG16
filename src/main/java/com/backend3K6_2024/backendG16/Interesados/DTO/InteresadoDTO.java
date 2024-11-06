package com.backend3K6_2024.backendG16.Interesados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InteresadoDTO {
    private long id;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private String restringido;
    private String nroLicencia;
    private LocalDateTime fechaVencimientoLicencia;

}
