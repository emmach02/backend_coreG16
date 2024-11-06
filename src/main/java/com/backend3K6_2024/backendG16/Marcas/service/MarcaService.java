package com.backend3K6_2024.backendG16.Marcas.service;

import com.backend3K6_2024.backendG16.Marcas.DTO.MarcaDTO;
import com.backend3K6_2024.backendG16.Marcas.entity.Marca;
import com.backend3K6_2024.backendG16.Marcas.mapper.MarcaMapper;
import com.backend3K6_2024.backendG16.Marcas.repository.MarcaRepository;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    // - MÉTODOS GET -
    public List<MarcaDTO> getAll() {
        List<Marca> marca = marcaRepository.findAll();
        return marca.stream()
                .map(MarcaMapper::toDTO)
                .toList();
    }
}
