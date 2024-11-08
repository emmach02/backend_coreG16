package com.backend3K6_2024.backendG16.Interesados.service;

import com.backend3K6_2024.backendG16.Interesados.DTO.InteresadoDTO;
import com.backend3K6_2024.backendG16.Interesados.entity.Interesado;
import com.backend3K6_2024.backendG16.Interesados.mapper.InteresadoMapper;
import com.backend3K6_2024.backendG16.Interesados.repository.InteresadoRepository;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class InteresadoService {
    @Autowired
    private InteresadoRepository interesadoRepository;

    // - MÉTODOS GET -
    public List<InteresadoDTO> getAll() {
        List<Interesado> interesado = interesadoRepository.findAll();
        return interesado.stream()
                .map(InteresadoMapper::toDTO)
                .toList();
    }
}
