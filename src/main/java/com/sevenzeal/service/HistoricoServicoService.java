package com.sevenzeal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.HistoricoServicoResponse;
import com.sevenzeal.model.HistoricoServico;
import com.sevenzeal.repository.HistoricoServicoRepository;

@Service
public class HistoricoServicoService {

    @Autowired
    private HistoricoServicoRepository repository;

    public HistoricoServicoResponse criar(Long veiculoId, Long servicoId, Long esteticaId, LocalDate dataRealizacao, LocalDate proximaManutencao) {
        HistoricoServico h = new HistoricoServico();
        h.setVeiculoId(veiculoId);
        h.setServicoId(servicoId);
        h.setEsteticaId(esteticaId);
        h.setDataRealizacao(dataRealizacao);
        h.setProximaManutencao(proximaManutencao);

        HistoricoServico salvo = repository.save(h);
        return toResponse(salvo);
    }

    public List<HistoricoServicoResponse> listarPorVeiculo(Long veiculoId) {
        return repository.findByVeiculoId(veiculoId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<HistoricoServicoResponse> listarPorEstetica(Long esteticaId) {
        return repository.findByEsteticaId(esteticaId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<HistoricoServicoResponse> listarVencimentos(LocalDate dataLimite) {
        return repository.findByProximaManutencaoLessThanEqual(dataLimite).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private HistoricoServicoResponse toResponse(HistoricoServico h) {
        return new HistoricoServicoResponse(h.getId(), h.getVeiculoId(), h.getServicoId(), h.getEsteticaId(), h.getDataRealizacao(), h.getProximaManutencao());
    }
}
