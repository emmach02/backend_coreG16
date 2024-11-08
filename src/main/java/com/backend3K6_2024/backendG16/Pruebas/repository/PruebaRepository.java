package com.backend3K6_2024.backendG16.Pruebas.repository;

import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends  JpaRepository<Prueba, Integer>{

}

