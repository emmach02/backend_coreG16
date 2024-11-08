package com.backend3K6_2024.backendG16.Pruebas.entity;

import com.backend3K6_2024.backendG16.*;
import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Vehiculos.entity.Vehiculo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "PRUEBAS")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pruebas")
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int idPrueba;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_interesado")
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "fecha_hora_inicio")
    private Date fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;

    @Column(name = "comentarios")
    private String comentarios;
}
