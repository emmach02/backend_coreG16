package com.backend3K6_2024.backendG16.Marcas.repository;

import com.backend3K6_2024.backendG16.Marcas.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
