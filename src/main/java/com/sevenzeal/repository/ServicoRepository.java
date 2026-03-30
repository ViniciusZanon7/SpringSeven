package com.sevenzeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByEsteticaId(Long esteticaId);
}
