package com.backend3K6_2024.backendG16.Posiciones.entity;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posiciones")

public class Posicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idPosicion;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;
}
