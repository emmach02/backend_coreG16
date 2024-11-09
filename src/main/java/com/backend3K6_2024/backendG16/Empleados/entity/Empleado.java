package com.backend3K6_2024.backendG16.Empleados.entity;

import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "EMPLEADOS")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "legajo")
    private Integer legajo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono_contacto")
    private String telefonoContacto;

    @OneToMany(mappedBy = "empleado")
    private List<Prueba> pruebas;


}
