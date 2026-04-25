package com.sevenzeal.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.NotificacaoResponse;
import com.sevenzeal.dto.HistoricoServicoResponse;
import com.sevenzeal.model.Notificacao;
import com.sevenzeal.repository.HistoricoProprietarioRepository;
import com.sevenzeal.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    @Autowired
    private HistoricoServicoService historicoServicoService;

    @Autowired
    private HistoricoProprietarioRepository proprietarioRepository;

    public NotificacaoResponse criar(Long usuarioId, String mensagem) {
        Notificacao n = new Notificacao();
        n.setUsuarioId(usuarioId);
        n.setMensagem(mensagem);
        n.setStatus("pendente");
        n.setCriadoEm(LocalDateTime.now());

        Notificacao salvo = repository.save(n);
        return toResponse(salvo);
    }

    public List<NotificacaoResponse> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<NotificacaoResponse> listarPendentes() {
        return repository.findByStatus("pendente").stream().map(this::toResponse).collect(Collectors.toList());
    }

    private NotificacaoResponse toResponse(Notificacao n) {
        return new NotificacaoResponse(n.getId(), n.getUsuarioId(), n.getMensagem(), n.getStatus(), n.getCriadoEm());
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void gerarNotificacoesDeManutencao() {
        LocalDate hoje = LocalDate.now();
        historicoServicoService.listarVencimentos(hoje).forEach(h -> {
            String mensagem = String.format("Manutenção de serviço #%d no veículo #%d está vencida ou próxima (data %s).", h.servicoId, h.veiculoId, h.proximaManutencao);
            proprietarioRepository.findByVeiculo_IdAndDataFimIsNull(h.veiculoId)
                .ifPresent(p -> {
                    criar(p.getUsuario().getId(), mensagem);
    });
        });
    }
}
