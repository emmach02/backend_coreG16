package com.backend3K6_2024.backendG16.Posiciones.service;

import com.backend3K6_2024.backendG16.Posiciones.DTO.PosicionDTO;
import com.backend3K6_2024.backendG16.Posiciones.entity.Posicion;
import com.backend3K6_2024.backendG16.Posiciones.mapper.PosicionMapper;
import com.backend3K6_2024.backendG16.Posiciones.repository.PosicionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service

public class PosicionService {

    @Autowired
    private PosicionRepository posicionRepository;

    public List<PosicionDTO> getAll(){
        List<Posicion> posicions = posicionRepository.findAll();
        return posicions.stream()
                .map(PosicionMapper::toDTO)
                .toList();
    }
}
