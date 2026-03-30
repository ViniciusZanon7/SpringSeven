package com.sevenzeal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.EsteticaRequest;
import com.sevenzeal.dto.EsteticaResponse;
import com.sevenzeal.model.Estetica;
import com.sevenzeal.repository.EsteticaRepository;

@Service
public class EsteticaService {

    @Autowired
    private EsteticaRepository repository;

    public EsteticaResponse criar(EsteticaRequest request) {
        Estetica e = new Estetica();
        e.setUsuarioId(request.usuarioId);
        e.setNome(request.nome);
        e.setEndereco(request.endereco);
        e.setCidade(request.cidade);
        e.setTelefone(request.telefone);
        e.setCriadoEm(LocalDateTime.now());

        Estetica salvo = repository.save(e);
        return toResponse(salvo);
    }

    public List<EsteticaResponse> listarTodos() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<EsteticaResponse> listarPorDono(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EsteticaResponse obter(Long id) {
        return repository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Estética não encontrada"));
    }

    private EsteticaResponse toResponse(Estetica e) {
        return new EsteticaResponse(e.getId(), e.getUsuarioId(), e.getNome(), e.getEndereco(), e.getCidade(), e.getTelefone(), e.getCriadoEm());
    }
}
