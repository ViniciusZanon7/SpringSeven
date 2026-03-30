package com.sevenzeal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevenzeal.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
    Optional<Veiculo> findByCodigoPublico(String codigoPublico);
}