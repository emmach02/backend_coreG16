package com.backend3K6_2024.backendG16.Modelos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "modelos")

public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idModelo;

    @Column(name = "id_marca")
    private String idMarca;

    @Column(name = "descripcion")
    private String descripcion;
}
