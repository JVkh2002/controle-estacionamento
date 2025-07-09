package com.estacionamento.repository;

import com.estacionamento.model.EstacionamentoRegistro;
import com.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRegistroRepository extends JpaRepository<EstacionamentoRegistro, Long> {
    List<EstacionamentoRegistro> findByHoraSaidaIsNull(); // Veículos no pátio
    Optional<EstacionamentoRegistro> findTopByVeiculoAndHoraSaidaIsNullOrderByHoraEntradaDesc(Veiculo veiculo);
}
