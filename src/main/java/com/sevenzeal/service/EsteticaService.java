package com.sevenzeal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.EsteticaRequest;
import com.sevenzeal.dto.EsteticaResponse;
import com.sevenzeal.model.Estetica;
import com.sevenzeal.model.User;
import com.sevenzeal.repository.EsteticaRepository;
import com.sevenzeal.repository.UserRepository;

@Service
public class EsteticaService {

    @Autowired
    private EsteticaRepository repository;

    @Autowired
    private UserRepository userRepository;

    public EsteticaResponse criar(EsteticaRequest request) {

    User dono = userRepository.findById(request.usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    Estetica e = new Estetica();
    e.setUsuario(dono);
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
        return repository.findByUsuario_Id(usuarioId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EsteticaResponse obter(Long id) {
        return repository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Estética não encontrada"));
    }

    private EsteticaResponse toResponse(Estetica e) {
        return new EsteticaResponse(e.getId(), e.getUsuario().getId(), e.getNome(), e.getEndereco(), e.getCidade(), e.getTelefone(), e.getCriadoEm());
    }
}
