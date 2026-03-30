package com.sevenzeal.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.model.HistoricoProprietario;
import com.sevenzeal.repository.HistoricoProprietarioRepository;

@Service
public class HistoricoProprietarioService {

    @Autowired
    private HistoricoProprietarioRepository repository;

    public void transferirPropriedade(Long veiculoId, Long novoUsuarioId) {

        // 🔥 fecha dono atual (se existir)
        repository.findByVeiculoIdAndDataFimIsNull(veiculoId)
            .ifPresent(atual -> {
                atual.setDataFim(LocalDate.now());
                repository.save(atual);
            });

        // 🔥 cria novo dono
        HistoricoProprietario novo = new HistoricoProprietario();
        novo.setVeiculoId(veiculoId);
        novo.setUsuarioId(novoUsuarioId);
        novo.setDataInicio(LocalDate.now());

        repository.save(novo);
    }
}