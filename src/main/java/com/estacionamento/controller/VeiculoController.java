package com.estacionamento.controller;

import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping
    public Veiculo cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }
}
