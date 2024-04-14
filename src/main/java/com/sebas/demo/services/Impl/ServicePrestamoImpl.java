package com.sebas.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebas.demo.config.PrestamoDTOConverter;
import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.repositories.RepositoryPrestamo;
import com.sebas.demo.repositories.entities.Prestamo;
import com.sebas.demo.resources.EstadoPrestamo;
import com.sebas.demo.services.ServicePrestamo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePrestamoImpl implements ServicePrestamo{
    
    @Autowired
    private RepositoryPrestamo repositoryPrestamo;

    @Autowired
    private PrestamoDTOConverter convert;
    
    @Override
    @Transactional(readOnly = true)
    public List<PrestamoListDTO> findAll() {
        List<Prestamo> prestamos = (List<Prestamo>) repositoryPrestamo.findAll();
        return prestamos.stream()
                    .map(prestamo -> convert.convertPrestamoDTO(prestamo))
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoListDTO> listarPrestamosPorEstado(EstadoPrestamo estado) {
        List<Prestamo> prestamos = repositoryPrestamo.findByEstadoPrestamo(estado);
        return prestamos.stream()
                    .map(prestamo -> convert.convertPrestamoDTO(prestamo))
                    .toList();
    }
    
}
