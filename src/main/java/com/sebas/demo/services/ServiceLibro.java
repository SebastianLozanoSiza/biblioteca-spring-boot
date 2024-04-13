package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.LibroDTO;
import com.sebas.demo.repositories.entities.Libro;

public interface ServiceLibro {
    
    List<LibroDTO> findAll();

    Libro findById(Long id);

    LibroDTO save(LibroDTO libroDTO);

    Libro update(Long id, Libro libro);

    void delete(Long id);
}
