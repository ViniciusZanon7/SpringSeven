package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.HistoricoServicoResponse;
import com.sevenzeal.service.HistoricoServicoService;

@RestController
@RequestMapping("/historico-servicos")
public class HistoricoServicoController {

    @Autowired
    private HistoricoServicoService service;

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<HistoricoServicoResponse>> listarPorVeiculo(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(service.listarPorVeiculo(veiculoId));
    }

    @GetMapping("/estetica/{esteticaId}")
    public ResponseEntity<List<HistoricoServicoResponse>> listarPorEstetica(@PathVariable Long esteticaId) {
        return ResponseEntity.ok(service.listarPorEstetica(esteticaId));
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<HistoricoServicoResponse>> listarVencidos() {
        return ResponseEntity.ok(service.listarVencimentos(java.time.LocalDate.now()));
    }
}
