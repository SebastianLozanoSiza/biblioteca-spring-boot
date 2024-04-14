package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.PrestamoListDTO;

public interface ServicePrestamo {
    
    List<PrestamoListDTO> findAll();

}
