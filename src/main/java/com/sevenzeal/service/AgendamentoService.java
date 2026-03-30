package com.sevenzeal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.AgendamentoRequest;
import com.sevenzeal.dto.AgendamentoResponse;
import com.sevenzeal.model.Agendamento;
import com.sevenzeal.repository.AgendamentoRepository;
import com.sevenzeal.repository.ServicoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private HistoricoServicoService historicoService;

    public AgendamentoResponse criar(AgendamentoRequest request) {

        // ❌ impedir horário duplicado
        if (repository.findByEsteticaIdAndDataAgendamentoAndHoraAgendamento(
                request.esteticaId,
                request.dataAgendamento,
                request.horaAgendamento
        ).isPresent()) {
            throw new RuntimeException("Horário já ocupado");
        }

        Agendamento a = new Agendamento();
        a.setUsuarioId(request.usuarioId);
        a.setVeiculoId(request.veiculoId);
        a.setEsteticaId(request.esteticaId);
        a.setServicoId(request.servicoId);
        a.setDataAgendamento(request.dataAgendamento);
        a.setHoraAgendamento(request.horaAgendamento);
        a.setStatus("pendente");
        a.setCriadoEm(LocalDateTime.now());

        Agendamento salvo = repository.save(a);

        return mapResponse(salvo);
    }

    public AgendamentoResponse atualizarStatus(Long id, String novoStatus) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setStatus(novoStatus);
        repository.save(agendamento);

        if ("concluido".equalsIgnoreCase(novoStatus)) {
            var servico = servicoRepository.findById(agendamento.getServicoId())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado para histórico"));
            var proximaManutencao = agendamento.getDataAgendamento().plusDays(servico.getIntervaloManutencaoDias() == null ? 0 : servico.getIntervaloManutencaoDias());
            historicoService.criar(agendamento.getVeiculoId(), agendamento.getServicoId(), agendamento.getEsteticaId(), agendamento.getDataAgendamento(), proximaManutencao);
        }

        return mapResponse(agendamento);
    }

    public AgendamentoResponse obter(Long id) {
        return repository.findById(id).map(this::mapResponse)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public List<Agendamento> listarPorEstetica(Long esteticaId) {
        return repository.findAll().stream().filter(a -> a.getEsteticaId().equals(esteticaId)).toList();
    }

    private AgendamentoResponse mapResponse(Agendamento salvo) {
        return new AgendamentoResponse(
                salvo.getId(),
                salvo.getUsuarioId(),
                salvo.getVeiculoId(),
                salvo.getDataAgendamento(),
                salvo.getHoraAgendamento(),
                salvo.getStatus()
        );
    }
}
