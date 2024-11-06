package com.backend3K6_2024.backendG16.Modelos.entity;

public class Modelo {
import com.backend3K6_2024.backendG16.Marcas.entity.Marca;
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
    private Marca marca;

    @Column(name = "descripcion")
    private String descripcion;

}
