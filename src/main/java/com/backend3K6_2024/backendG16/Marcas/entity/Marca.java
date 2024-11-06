package com.backend3K6_2024.backendG16.Marcas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MARCAS")

public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idMarca;

    @Column(name = "NOMBRE")
    private String nombre;

}
