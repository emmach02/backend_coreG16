package com.backend3K6_2024.backendG16.Vehiculos.entity;

import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(generator = "VEHICULOS")
    @TableGenerator(name = "VEHICULOS", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "VEHICULOS",
            initialValue = 1, allocationSize = 1)

    @Column(name = "ID")
    private long id;

    @Column(name = "PATENTE")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "ID_MODELO", referencedColumnName = "ID")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo")
    private List<Prueba> pruebas;

}
