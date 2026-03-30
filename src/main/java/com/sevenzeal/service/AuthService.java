package com.sevenzeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.LoginRequest;
import com.sevenzeal.model.User;
import com.sevenzeal.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 🔥 valida senha (simples por enquanto)
        if (user.getSenha() == null || !user.getSenha().equals(request.senha)) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(user.getEmail(), user.getTipoUsuario());
    }
}