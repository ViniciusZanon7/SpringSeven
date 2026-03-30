package com.sevenzeal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.ServicoRequest;
import com.sevenzeal.dto.ServicoResponse;
import com.sevenzeal.model.Servico;
import com.sevenzeal.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public ServicoResponse criar(ServicoRequest request) {
        Servico s = new Servico();
        s.setEsteticaId(request.esteticaId);
        s.setNome(request.nome);
        s.setDescricao(request.descricao);
        s.setPreco(request.preco);
        s.setDuracaoMinutos(request.duracaoMinutos);
        s.setIntervaloManutencaoDias(request.intervaloManutencaoDias);

        Servico salvo = repository.save(s);
        return toResponse(salvo);
    }

    public List<ServicoResponse> listarPorEstetica(Long esteticaId) {
        return repository.findByEsteticaId(esteticaId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ServicoResponse obter(Long id){
        return repository.findById(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    private ServicoResponse toResponse(Servico s) {
        return new ServicoResponse(s.getId(), s.getEsteticaId(), s.getNome(), s.getDescricao(), s.getPreco(), s.getDuracaoMinutos(), s.getIntervaloManutencaoDias());
    }
}
