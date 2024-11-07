package com.backend3K6_2024.backendG16.Modelos.repository;

import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
}
