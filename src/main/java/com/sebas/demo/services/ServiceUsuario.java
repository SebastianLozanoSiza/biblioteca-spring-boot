package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.UsuarioListDTO;
import com.sebas.demo.dto.UsuarioSaveDTO;
import com.sebas.demo.repositories.entities.Usuario;

public interface ServiceUsuario {
    
    List<UsuarioListDTO> findAll();

    Usuario findById(Long id);

    UsuarioSaveDTO save(UsuarioSaveDTO usuarioSaveDTO);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);
}
