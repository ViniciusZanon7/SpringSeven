package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.EsteticaRequest;
import com.sevenzeal.dto.EsteticaResponse;
import com.sevenzeal.service.EsteticaService;

@RestController
@RequestMapping("/esteticas")
public class EsteticaController {

    @Autowired
    private EsteticaService service;

    @PreAuthorize("hasAuthority('ESTETICA') or hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<EsteticaResponse> criar(@RequestBody EsteticaRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ESTETICA')")
    @GetMapping
    public ResponseEntity<List<EsteticaResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ESTETICA')")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EsteticaResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorDono(usuarioId));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ESTETICA')")
    @GetMapping("/{id}")
    public ResponseEntity<EsteticaResponse> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }
}

