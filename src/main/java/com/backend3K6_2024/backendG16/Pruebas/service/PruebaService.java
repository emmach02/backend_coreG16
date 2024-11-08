package com.backend3K6_2024.backendG16.Pruebas.service;

import com.backend3K6_2024.backendG16.Pruebas.DTO.PruebaDTO;
import com.backend3K6_2024.backendG16.Pruebas.entity.Prueba;
import com.backend3K6_2024.backendG16.Pruebas.repository.PruebaRepository;
import com.backend3K6_2024.backendG16.Pruebas.mapper.PruebaMapper;
import com.backend3K6_2024.backendG16.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Service
public class PruebaService {
    @Autowired
    private PruebaRepository pruebaRepository;

    //-METODOS GET-
    public List<PruebaDTO> getAll(){
        List<Prueba> pruebas = pruebaRepository.findAll();
        return pruebas.stream()
                .map(PruebaMapper::toDTO)
                .toList();
    }

    //-METODOS POST-

    public PruebaDTO addPrueba(@RequestBody PruebaDTO pruebaDTO) {
        Prueba prueba = PruebaMapper.toEntity(pruebaDTO);
        prueba = pruebaRepository.save(prueba);
        return PruebaMapper.toDTO(prueba);
    }

}
