package com.sevenzeal.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Optional<Agendamento> findByEsteticaIdAndDataAgendamentoAndHoraAgendamento(
        Long esteticaId,
        LocalDate data,
        LocalTime hora
    );
}