package com.backend3K6_2024.backendG16.Pruebas.DTO;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(makeFinal = true)
@ToString

public class PruebaDTO {
    Integer idPrueba;
    Vehiculo vehiculo;
    Interesado interesado;
    Empleado empleado;
    Date fechaHoraInicio;
    Date fechaHoraFin;
    String comentarios;
}
