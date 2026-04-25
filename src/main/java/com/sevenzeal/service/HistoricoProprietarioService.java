package com.sevenzeal.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.model.HistoricoProprietario;
import com.sevenzeal.model.User;
import com.sevenzeal.model.Veiculo;
import com.sevenzeal.repository.HistoricoProprietarioRepository;
import com.sevenzeal.repository.UserRepository;
import com.sevenzeal.repository.VeiculoRepository;

@Service
public class HistoricoProprietarioService {

    @Autowired
    private HistoricoProprietarioRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void transferirPropriedade(Long veiculoId, Long novoUsuarioId) {

        // fecha dono atual
        repository.findByVeiculo_IdAndDataFimIsNull(veiculoId)
            .ifPresent(atual -> {
                atual.setDataFim(LocalDate.now());
                repository.save(atual);
            });

        // busca entidades
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        User usuario = userRepository.findById(novoUsuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // cria novo vínculo
        HistoricoProprietario novo = new HistoricoProprietario();
        novo.setVeiculo(veiculo);
        novo.setUsuario(usuario);
        novo.setDataInicio(LocalDate.now());

        repository.save(novo);
    }
}