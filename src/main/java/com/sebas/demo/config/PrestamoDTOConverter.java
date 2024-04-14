package com.sebas.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.dto.PrestamoSaveDTO;
import com.sebas.demo.repositories.entities.Prestamo;

@Component
public class PrestamoDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    public PrestamoDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public PrestamoListDTO convertPrestamoDTO(Prestamo prestamo){
        PrestamoListDTO prestamoDTO = dbm.map(prestamo, PrestamoListDTO.class);
        if(prestamo.getUsuario() != null){
            prestamoDTO.setNombreUsuario(prestamo.getUsuario().getNombre() + " " + prestamo.getUsuario().getApellido());
        }
        if (prestamo.getLibro() != null) {
            prestamoDTO.setNombreLibro(prestamo.getLibro().getTitulo());
        }
        return prestamoDTO;
    }

    public Prestamo convertPrestamoEntity(PrestamoListDTO prestamoDTO){
        return dbm.map(prestamoDTO, Prestamo.class);   
    }

    public PrestamoSaveDTO converPrestamoSaveDTO(Prestamo prestamo){
        PrestamoSaveDTO prestamoSaveDTO = dbm.map(prestamo, PrestamoSaveDTO.class);
        if (prestamo.getUsuario() != null) {
            prestamoSaveDTO.setUsuarioId(prestamo.getUsuario().getId());
        }
        if (prestamo.getLibro() != null) {
            prestamoSaveDTO.setLibroId(prestamo.getLibro().getId());
        }
        return prestamoSaveDTO;
    }

    public Prestamo convertPrestamoSaveEntity(PrestamoSaveDTO prestamoSaveDTO){
        return dbm.map(prestamoSaveDTO, Prestamo.class);   
    }
}
