package com.sebas.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.demo.dto.PrestamoListDTO;
import com.sebas.demo.dto.PrestamoSaveDTO;
import com.sebas.demo.resources.EstadoPrestamo;
import com.sebas.demo.services.ServicePrestamo;

import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PrestamoSaveDTO prestamo, BindingResult result){

        PrestamoSaveDTO prestamoNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {

            prestamoNew = servicePrestamo.save(prestamo);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar los inserts en la base de datos de LibroDTO");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Prestamo creado exitosamente");
        response.put("prestamo", prestamoNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
