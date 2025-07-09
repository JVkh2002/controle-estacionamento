package com.estacionamento.controller;

import com.estacionamento.model.EstacionamentoRegistro;
import com.estacionamento.service.EstacionamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService service;

    public EstacionamentoController(EstacionamentoService service) {
        this.service = service;
    }

    @PostMapping("/entrada")
    public EstacionamentoRegistro registrarEntrada(@RequestParam String placa) {
        return service.registrarEntrada(placa);
    }

    // ...

    @PostMapping("/saida")
    public EstacionamentoRegistro registrarSaida(@RequestParam String placa) {
        return service.registrarSaida(placa);
    }

    @GetMapping("/ativos")
    public List<EstacionamentoRegistro> listarAtivos() {
        return service.listarAtivos();
    }

    @GetMapping("/historico")
    public List<EstacionamentoRegistro> listarHistorico() {
        return service.listarHistorico();
    }
}
