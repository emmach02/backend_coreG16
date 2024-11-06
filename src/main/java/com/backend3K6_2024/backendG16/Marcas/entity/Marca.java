package com.backend3K6_2024.backendG16.Marcas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "marcas")

public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idMarca;

    @Column(name = "nombre")
    private String nombre;

}
