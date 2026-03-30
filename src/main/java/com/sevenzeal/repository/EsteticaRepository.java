package com.sevenzeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.Estetica;

public interface EsteticaRepository extends JpaRepository<Estetica, Long> {
    List<Estetica> findByUsuarioId(Long usuarioId);
}
