package com.sevenzeal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.HistoricoProprietario;

public interface HistoricoProprietarioRepository extends JpaRepository<HistoricoProprietario, Long> {

    Optional<HistoricoProprietario> findByVeiculoIdAndDataFimIsNull(Long veiculoId);

    List<HistoricoProprietario> findByUsuarioId(Long usuarioId);
    List<HistoricoProprietario> findByVeiculoId(Long veiculoId);
}