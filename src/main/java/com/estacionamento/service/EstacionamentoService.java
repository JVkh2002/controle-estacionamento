package com.estacionamento.service;

import com.estacionamento.model.EstacionamentoRegistro;
import com.estacionamento.model.Veiculo;
import com.estacionamento.repository.EstacionamentoRegistroRepository;
import com.estacionamento.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {

    private final VeiculoRepository veiculoRepository;
    private final EstacionamentoRegistroRepository registroRepository;

    public EstacionamentoService(VeiculoRepository veiculoRepository, EstacionamentoRegistroRepository registroRepository) {
        this.veiculoRepository = veiculoRepository;
        this.registroRepository = registroRepository;
    }

    @Transactional
    public EstacionamentoRegistro registrarEntrada(String placa) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findByPlaca(placa);
        if (veiculoOpt.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado");
        }
        Veiculo veiculo = veiculoOpt.get();

        boolean jaEstacionado = registroRepository
                .findTopByVeiculoAndHoraSaidaIsNullOrderByHoraEntradaDesc(veiculo)
                .isPresent();

        if (jaEstacionado) {
            throw new RuntimeException("Veículo já está estacionado.");
        }

        EstacionamentoRegistro registro = new EstacionamentoRegistro();
        registro.setVeiculo(veiculo);
        registro.setHoraEntrada(LocalDateTime.now());

        return registroRepository.save(registro);
    }

    @Transactional
    public EstacionamentoRegistro registrarSaida(String placa) {
        Optional<Veiculo> veiculoOpt = veiculoRepository.findByPlaca(placa);
        if (veiculoOpt.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado");
        }
        Veiculo veiculo = veiculoOpt.get();

        EstacionamentoRegistro registro = registroRepository
                .findTopByVeiculoAndHoraSaidaIsNullOrderByHoraEntradaDesc(veiculo)
                .orElseThrow(() -> new RuntimeException("Veículo não está no estacionamento."));

        LocalDateTime agora = LocalDateTime.now();
        registro.setHoraSaida(agora);

        double valor = calcularValor(registro.getHoraEntrada(), agora);
        registro.setValorPago(valor);

        return registroRepository.save(registro);
    }

    public double calcularValor(LocalDateTime entrada, LocalDateTime saida) {
        long minutos = Duration.between(entrada, saida).toMinutes();
        if (minutos <= 60) return 10.0;
        long horasExtras = (minutos - 60 + 59) / 60;
        return 10.0 + horasExtras * 5.0;
    }

    public List<EstacionamentoRegistro> listarAtivos() {
        return registroRepository.findByHoraSaidaIsNull();
    }

    public List<EstacionamentoRegistro> listarHistorico() {
        return registroRepository.findAll();
    }
}