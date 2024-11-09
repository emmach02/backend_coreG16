package com.backend3K6_2024.backendG16.Interesados.entity;

import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="INTERESADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Interesado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "documento")
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "restringido")
    private String restringido;
    @Column (name = "nro_licencia")
    private String nroLicencia;
    @Column(name = "fecha_vencimiento_licencia")
    private LocalDateTime fechaVencimientoLicencia;
}
