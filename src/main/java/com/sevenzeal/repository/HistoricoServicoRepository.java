package com.sevenzeal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.HistoricoServico;

public interface HistoricoServicoRepository extends JpaRepository<HistoricoServico, Long> {
    List<HistoricoServico> findByVeiculoId(Long veiculoId);
    List<HistoricoServico> findByEsteticaId(Long esteticaId);
    List<HistoricoServico> findByProximaManutencaoLessThanEqual(LocalDate date);
}
