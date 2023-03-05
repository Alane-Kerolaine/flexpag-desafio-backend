package com.flexpag.paymentscheduler.model.dto;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagamentoGetDTO {
    private String instituicaoFavorecida;
    private LocalDate dataAgendamento;
    private StatusPagamento statusPagamento;
}
