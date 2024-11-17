package com.backend3K6_2024.backendG16.Posiciones.repository;

import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
    List<Posicion> findAllByIdVehiculo(Integer idVehiculo);
}
