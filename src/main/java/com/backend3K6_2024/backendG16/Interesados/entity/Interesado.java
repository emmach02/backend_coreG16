package com.backend3K6_2024.backendG16.Interesados.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name="INTERESADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Interesado {

    @Id
    @GeneratedValue(generator = "INTERESADOS")
    @TableGenerator(name = "INTERESADOS", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "INTERESADOS",
            initialValue = 1, allocationSize = 1)

    @Column(name = "ID")
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "RESTRINGIDO")
    private String restringido;
    @Column (name = "NRO_LICENCIA")
    private String nroLicencia;
    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDateTime fechaVencimientoLicencia;

}
