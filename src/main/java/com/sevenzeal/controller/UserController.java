package com.sevenzeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.UserRequest;
import com.sevenzeal.dto.UserResponse;
import com.sevenzeal.service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> criar(@RequestBody UserRequest request) {
        return ResponseEntity.ok(service.criarUsuario(request));
    }

    @PatchMapping("/{id}/tipo")
    public ResponseEntity<UserResponse> atualizarTipo(
        @PathVariable Long id,
        @RequestParam String tipoUsuario
    ) {
        return ResponseEntity.ok(service.atualizarTipoUsuario(id, tipoUsuario));
    }
}
