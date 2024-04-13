package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Prestamo;

public interface RepositoryPrestamo extends CrudRepository<Prestamo,Long>{
    
}
