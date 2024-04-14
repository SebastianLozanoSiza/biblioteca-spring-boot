package com.sebas.demo.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebas.demo.config.PrestamoDTOConverter;
import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.dto.PrestamoSaveDTO;
import com.sebas.demo.repositories.RepositoryLibro;
import com.sebas.demo.repositories.RepositoryPrestamo;
import com.sebas.demo.repositories.RepositoryUsuario;
import com.sebas.demo.repositories.entities.Libro;
import com.sebas.demo.repositories.entities.Prestamo;
import com.sebas.demo.repositories.entities.Usuario;
import com.sebas.demo.resources.EstadoPrestamo;
import com.sebas.demo.services.ServicePrestamo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePrestamoImpl implements ServicePrestamo{
    
    @Autowired
    private RepositoryPrestamo repositoryPrestamo;
    private RepositoryUsuario repositoryUsuario;
    private RepositoryLibro repositoryLibro;

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

    @Override
    @Transactional
    public PrestamoSaveDTO save(PrestamoSaveDTO prestamoSaveDTO) {
        Optional<Usuario> usuario = repositoryUsuario.findById(prestamoSaveDTO.getUsuarioId());
        Optional<Libro> libro = repositoryLibro.findById(prestamoSaveDTO.getLibroId());
        Prestamo result = convert.convertPrestamoSaveEntity(prestamoSaveDTO);
        if (!usuario.isPresent()) {
            System.out.println("Error, usuario no existente");
        }
        if (!libro.isPresent()) {
            System.out.println("Error, libro no existente");
        }
        result.setUsuario(usuario.get());
        result.setLibro(libro.get());
        return convert.converPrestamoSaveDTO(repositoryPrestamo.save(result));
    }

    @Override
    @Transactional
    public Prestamo update(Long id, Prestamo prestamo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
