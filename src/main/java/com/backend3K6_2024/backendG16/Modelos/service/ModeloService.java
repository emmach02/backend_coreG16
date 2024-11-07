package com.backend3K6_2024.backendG16.Modelos.service;

import com.backend3K6_2024.backendG16.Modelos.DTO.ModeloDTO;
import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Modelos.mapper.ModeloMapper;
import com.backend3K6_2024.backendG16.Modelos.repository.ModeloRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    // - MÉTODOS GET -
    public List<ModeloDTO> getAll() {
        List<Modelo> modelos = modeloRepository.findAll();
        return modelos.stream()
                .map(ModeloMapper::toDTO)
                .toList();
    }
}
