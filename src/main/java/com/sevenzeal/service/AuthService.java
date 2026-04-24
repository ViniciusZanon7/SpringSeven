package com.sevenzeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(request.senha, user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(user.getEmail(), user.getTipoUsuario());
    }
}