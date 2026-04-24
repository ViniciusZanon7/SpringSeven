package com.sevenzeal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.UserRequest;
import com.sevenzeal.dto.UserResponse;
import com.sevenzeal.model.TipoUsuario;
import com.sevenzeal.model.User;
import com.sevenzeal.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserResponse criarUsuario(UserRequest request) {

        if (repository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (TipoUsuario.from(request.tipoUsuario) == null) {
            throw new RuntimeException("tipoUsuario inválido");
        }

        User user = new User();
        user.setNome(request.nome);
        user.setEmail(request.email);
        user.setTelefone(request.telefone);
        user.setTipoUsuario(TipoUsuario.from(request.tipoUsuario).name());
        user.setSenha(encoder.encode(request.senha));

        User saved = repository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getNome(),
                saved.getEmail(),
                saved.getTipoUsuario()
        );
    }

    public UserResponse atualizarTipoUsuario(Long userId, String tipoUsuario) {
        TipoUsuario tipo = TipoUsuario.from(tipoUsuario);
        if (tipo == null) {
            throw new RuntimeException("tipoUsuario inválido");
        }

        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setTipoUsuario(tipo.name());
        User saved = repository.save(user);

        return new UserResponse(saved.getId(), saved.getNome(), saved.getEmail(), saved.getTipoUsuario());
    }

    public List<UserResponse> listarUsuarios() {
        return repository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getNome(),
                        user.getEmail(),
                        user.getTipoUsuario()
                    ))
                    .collect(Collectors.toList());
    }
}