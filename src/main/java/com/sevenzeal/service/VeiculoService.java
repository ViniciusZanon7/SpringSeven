package com.sevenzeal.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenzeal.dto.VeiculoRequest;
import com.sevenzeal.dto.VeiculoResponse;
import com.sevenzeal.model.Veiculo;
import com.sevenzeal.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public VeiculoResponse criar(VeiculoRequest request) {

        if (repository.findByPlaca(request.placa).isPresent()) {
            throw new RuntimeException("Veículo já cadastrado com essa placa");
        }

        Veiculo v = new Veiculo();
        v.setTipoVeiculo(request.tipoVeiculo);
        v.setMarca(request.marca);
        v.setModelo(request.modelo);
        v.setAno(request.ano);
        v.setPlaca(request.placa);

        // 🔥 GERA CÓDIGO PÚBLICO (futuro QR)
        v.setCodigoPublico(UUID.randomUUID().toString().substring(0, 8));

        Veiculo salvo = repository.save(v);

        return new VeiculoResponse(
            salvo.getId(),
            salvo.getTipoVeiculo(),
            salvo.getMarca(),
            salvo.getModelo(),
            salvo.getPlaca(),
            salvo.getCodigoPublico()
        );
    }
    public List<VeiculoResponse> listarTodos() {
    return repository.findAll().stream().map(v ->
        new VeiculoResponse(
            v.getId(),
            v.getTipoVeiculo(),
            v.getMarca(),
            v.getModelo(),
            v.getPlaca(),
            v.getCodigoPublico()
        )
    ).toList();
}
}