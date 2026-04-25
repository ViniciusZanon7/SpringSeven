package com.sevenzeal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.EstatisticaUsuarioResponse;
import com.sevenzeal.dto.EstatisticaVeiculoResponse;
import com.sevenzeal.model.HistoricoProprietario;
import com.sevenzeal.model.HistoricoServico;
import com.sevenzeal.repository.HistoricoProprietarioRepository;
import com.sevenzeal.repository.HistoricoServicoRepository;

@Service
public class EstatisticaService {

    @Autowired
    private HistoricoProprietarioRepository proprietarioRepository;

    @Autowired
    private HistoricoServicoRepository historicoServicoRepository;

    public EstatisticaVeiculoResponse estatisticasVeiculo(Long veiculoId) {
        List<HistoricoServico> historicos = historicoServicoRepository.findByVeiculoId(veiculoId);
        Long total = (long) historicos.size();
        LocalDate ultima = historicos.stream().map(HistoricoServico::getDataRealizacao).max(LocalDate::compareTo).orElse(null);
        LocalDate prox = historicos.stream().map(HistoricoServico::getProximaManutencao).min(LocalDate::compareTo).orElse(null);

        return new EstatisticaVeiculoResponse(veiculoId, total, ultima, prox);
    }

    public EstatisticaUsuarioResponse estatisticasUsuario(Long usuarioId) {
        List<HistoricoProprietario> proprietarios = proprietarioRepository.findByUsuario_Id(usuarioId);
        Long totalVeiculos = proprietarios.stream().map(p -> p.getVeiculo().getId()).distinct().count();

        Long totalServicos = proprietarios.stream()
                .flatMap(p -> historicoServicoRepository.findByVeiculoId(p.getVeiculo().getId()).stream())
                .count();

        return new EstatisticaUsuarioResponse(usuarioId, totalVeiculos, totalServicos);
    }

    public List<EstatisticaVeiculoResponse> veiculosParaPromocao(int diasUltimoServico) {
        LocalDate limite = LocalDate.now().minusDays(diasUltimoServico);
        return historicoServicoRepository.findByProximaManutencaoLessThanEqual(LocalDate.now()).stream()
                .filter(h -> h.getDataRealizacao() == null || h.getDataRealizacao().isBefore(limite))
                .map(h -> new EstatisticaVeiculoResponse(h.getVeiculoId(), 1L, h.getDataRealizacao(), h.getProximaManutencao()))
                .toList();
    }

    public List<EstatisticaUsuarioResponse> usuariosParaPromocao(long minServicos) {
    return proprietarioRepository.findAll().stream()
        .collect(Collectors.groupingBy(
            p -> p.getUsuario().getId(),
            Collectors.mapping(p -> p.getVeiculo().getId(), Collectors.toSet())
        ))
        .entrySet().stream()
        .map(entry -> {
            Long usuarioId = entry.getKey();

            long totalServicos = entry.getValue().stream()
                .flatMap(vid -> historicoServicoRepository.findByVeiculoId(vid).stream())
                .count();

            return new EstatisticaUsuarioResponse(
                usuarioId,
                (long) entry.getValue().size(),
                totalServicos
            );
        })
        .filter(r -> r.totalServicos >= minServicos)
        .toList();
}
}
