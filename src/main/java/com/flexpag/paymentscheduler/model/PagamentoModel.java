package com.flexpag.paymentscheduler.model;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "pagamento")
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String instituicaoFavorecida;
    private LocalDate dataAgendamento;
    private StatusPagamento statusPagamento = StatusPagamento.pending;
    private double valorFatura;
    private double saldoFatura = 0.0;
}
