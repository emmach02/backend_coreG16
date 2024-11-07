package com.backend3K6_2024.backendG16.Modelos.entity;
import com.backend3K6_2024.backendG16.Marcas.entity.Marca;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "id_marca")
    @JsonBackReference
    private Marca marca;

    @Column(name = "descripcion")
    private String descripcion;

}
