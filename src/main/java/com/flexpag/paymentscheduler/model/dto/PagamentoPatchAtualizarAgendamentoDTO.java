package com.flexpag.paymentscheduler.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PagamentoPatchAtualizarAgendamentoDTO {
    private Long id;
    private LocalDate dataAgendamento;
}
