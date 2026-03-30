package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.ServicoRequest;
import com.sevenzeal.dto.ServicoResponse;
import com.sevenzeal.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @PostMapping
    public ResponseEntity<ServicoResponse> criar(@RequestBody ServicoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @GetMapping("/estetica/{esteticaId}")
    public ResponseEntity<List<ServicoResponse>> listarPorEstetica(@PathVariable Long esteticaId) {
        return ResponseEntity.ok(service.listarPorEstetica(esteticaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }
}
