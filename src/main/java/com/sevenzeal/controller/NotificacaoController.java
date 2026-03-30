package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.NotificacaoResponse;
import com.sevenzeal.service.NotificacaoService;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacaoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<NotificacaoResponse>> listarPendentes() {
        return ResponseEntity.ok(service.listarPendentes());
    }
}
