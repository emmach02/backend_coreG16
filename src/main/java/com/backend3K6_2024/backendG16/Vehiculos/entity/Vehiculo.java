package com.backend3K6_2024.backendG16.Vehiculos.entity;

import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "vehiculos")

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "patente")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo")
    @JsonBackReference
    private List<Prueba> pruebas;

}
