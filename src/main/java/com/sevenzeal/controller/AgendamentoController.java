package com.sevenzeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.AgendamentoRequest;
import com.sevenzeal.dto.AgendamentoResponse;
import com.sevenzeal.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criar(@RequestBody AgendamentoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoResponse> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    @GetMapping("/estetica/{esteticaId}")
    public ResponseEntity<?> listarPorEstetica(@PathVariable Long esteticaId) {
        return ResponseEntity.ok(service.listarPorEstetica(esteticaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }
}