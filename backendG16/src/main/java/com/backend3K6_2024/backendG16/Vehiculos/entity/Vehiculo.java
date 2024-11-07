package com.backend3K6_2024.backendG16.Vehiculos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

import java.time.LocalDateTime;

public class Vehiculo {
    @Id
    @GeneratedValue(generator = "VEHICULOS")
    @TableGenerator(name = "VEHICULOS", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue = "VEHICULOS",
            initialValue = 1, allocationSize = 1)

    @Column(name = "ID")
    private long id;

    @Column(name = "PATENTE")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID")
    private Modelos modelos;
}
