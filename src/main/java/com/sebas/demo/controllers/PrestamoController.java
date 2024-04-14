package com.sebas.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.resources.EstadoPrestamo;
import com.sebas.demo.services.ServicePrestamo;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/prestamos")
@AllArgsConstructor
public class PrestamoController {

    private ServicePrestamo servicePrestamo;

    @GetMapping
    public ResponseEntity<List<PrestamoListDTO>> findAll(){
        List<PrestamoListDTO> findAll = servicePrestamo.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
        
    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<PrestamoListDTO>> listarPrestamosPorEstado(@PathVariable EstadoPrestamo estado) {
        List<PrestamoListDTO> prestamos = servicePrestamo.listarPrestamosPorEstado(estado);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }
    
}
