package com.backend3K6_2024.backendG16.Modelos.service;

import com.backend3K6_2024.backendG16.Modelos.DTO.ModeloDTO;
import com.backend3K6_2024.backendG16.Modelos.entity.Modelo;
import com.backend3K6_2024.backendG16.Modelos.mapper.ModeloMapper;
import com.backend3K6_2024.backendG16.Modelos.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    // Métodos Get //
    public List<ModeloDTO> getAll() {
        List<Modelo> modelos = modeloRepository.findAll();
        return modelos.stream()
                .map(ModeloMapper::toDTO)
                .toList();
    }
}
