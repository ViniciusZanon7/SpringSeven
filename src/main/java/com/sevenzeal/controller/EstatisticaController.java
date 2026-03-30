package com.sevenzeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sevenzeal.dto.EstatisticaUsuarioResponse;
import com.sevenzeal.dto.EstatisticaVeiculoResponse;
import com.sevenzeal.service.EstatisticaService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    private EstatisticaService service;

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<EstatisticaVeiculoResponse> estatisticasVeiculo(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(service.estatisticasVeiculo(veiculoId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<EstatisticaUsuarioResponse> estatisticasUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.estatisticasUsuario(usuarioId));
    }

    @GetMapping("/veiculos/promocoes")
    public ResponseEntity<List<EstatisticaVeiculoResponse>> veiculosPromocao(
            @RequestParam(defaultValue = "90") int diasUltimoServico
    ) {
        return ResponseEntity.ok(service.veiculosParaPromocao(diasUltimoServico));
    }

    @GetMapping("/usuarios/promocoes")
    public ResponseEntity<List<EstatisticaUsuarioResponse>> usuariosPromocao(
            @RequestParam(defaultValue = "5") long minServicos
    ) {
        return ResponseEntity.ok(service.usuariosParaPromocao(minServicos));
    }
}
