package com.sevenzeal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.HistoricoProprietario;

public interface HistoricoProprietarioRepository extends JpaRepository<HistoricoProprietario, Long> {

    Optional<HistoricoProprietario> findByVeiculo_IdAndDataFimIsNull(Long veiculoId);

    List<HistoricoProprietario> findByUsuario_Id(Long usuarioId);

    List<HistoricoProprietario> findByVeiculo_Id(Long veiculoId);
}