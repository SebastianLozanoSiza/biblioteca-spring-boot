package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libros")
public class Libro implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El titulo no puede estar vacio")
    @Column(nullable = false)
    private String titulo;

    @NotEmpty(message = "El autor no puede estar vacio")
    @Column(nullable = false)
    private String autor;

    @NotEmpty(message = "El genero no puede estar vacio")
    @Column(nullable = false)
    private String genero;

    @NotEmpty(message = "El año de publicacion no puede estar vacio")
    @Column(nullable = false)
    private Date añoPublicacion;

    @NotEmpty(message = "La cantidad disponible no puede estar vacia")
    @Column(nullable = false)
    private int cantidadDisponible;
}
