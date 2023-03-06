package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.dto.PagamentoGetDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPostRetornoDTO;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchAtualizarAgendamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchRealizarPagamentoDTO;
import com.flexpag.paymentscheduler.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    final PagamentoService pagamentoService;

    @PostMapping("/registrar-agendamento")
    public ResponseEntity<PagamentoPostRetornoDTO> agendarPagamento(@RequestBody PagamentoModel pagamento){
        return ResponseEntity.ok(pagamentoService.agendarPagamento(pagamento));
    }

    @DeleteMapping("excluir-agendamento")
    public void excluirAgendamento(@RequestParam Long id){
        pagamentoService.excluirAgendamento(id);
    }

    @PatchMapping("atualizar-agendamento")
    public ResponseEntity<PagamentoModel> atualizarAgendamento(@RequestParam Long id, @RequestBody PagamentoPatchAtualizarAgendamentoDTO pagamento){
        return ResponseEntity.ok(pagamentoService.atualizarAgendamento(id, pagamento));
    }

    @PatchMapping("realizar-pagamento")
    public ResponseEntity<PagamentoModel> realizarPagamento(@RequestParam Long id, @RequestBody PagamentoPatchRealizarPagamentoDTO pagamento){
        return ResponseEntity.ok(pagamentoService.realizarPagamento(id, pagamento));
    }

    @GetMapping("/consultar-status-pagamento")
    public ResponseEntity<PagamentoGetDTO> consultarStatusPagamento(@RequestParam Long id){
        return ResponseEntity.ok(pagamentoService.consultarStatusAgendamento(id));
    }


}
