package com.backend3K6_2024.backendG16.Empleados.repository;

import com.backend3K6_2024.backendG16.Empleados.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}