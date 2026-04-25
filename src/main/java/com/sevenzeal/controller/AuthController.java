package com.sevenzeal.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.LoginRequest;
import com.sevenzeal.dto.LoginResponse;
import com.sevenzeal.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = service.login(request);
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/forgot")
    public ResponseEntity<String> forgot(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        String fakeToken = UUID.randomUUID().toString();

        System.out.println("TOKEN RESET: " + fakeToken);

        return ResponseEntity.ok("Email enviado (simulado)");
}
    @PostMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody Map<String, String> body) {

        String token = body.get("token");
        String novaSenha = body.get("novaSenha");

    return ResponseEntity.ok("Senha alterada");
}
}