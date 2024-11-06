package com.backend3K6_2024.backendG16.Marcas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "MARCAS")
@AllArgsConstructor
@NoArgsConstructor

public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idMarca;

    @Column(name = "nombre")
    private String nombre;

}
