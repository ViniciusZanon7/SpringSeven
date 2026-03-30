package com.sevenzeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.service.HistoricoProprietarioService;

@RestController
@RequestMapping("/proprietarios")
public class HistoricoProprietarioController {

    @Autowired
    private HistoricoProprietarioService service;

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(
        @RequestParam Long veiculoId,
        @RequestParam Long usuarioId
    ) {
        service.transferirPropriedade(veiculoId, usuarioId);
        return ResponseEntity.ok("Propriedade transferida com sucesso");
    }
}