package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.VeiculoRequest;
import com.sevenzeal.dto.VeiculoResponse;
import com.sevenzeal.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoResponse> criar(@RequestBody VeiculoRequest request) {
        return ResponseEntity.ok(service.criar(request));
    }
    @GetMapping
    public List<VeiculoResponse> listar() {
    return service.listarTodos();
}
}