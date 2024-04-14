package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.dto.PrestamoSaveDTO;
import com.sebas.demo.repositories.entities.Prestamo;
import com.sebas.demo.resources.EstadoPrestamo;

public interface ServicePrestamo {
    
    List<PrestamoListDTO> findAll();

    List<PrestamoListDTO> listarPrestamosPorEstado(EstadoPrestamo estado);

    PrestamoSaveDTO save(PrestamoSaveDTO prestamoSaveDTO);

    Prestamo update(Long id, Prestamo prestamo);

    void delete(Long id);

}
