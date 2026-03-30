package com.sevenzeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUsuarioId(Long usuarioId);
    List<Notificacao> findByStatus(String status);
}
