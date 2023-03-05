package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.dto.PagamentoGetDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPostDTO;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchAtualizarAgendamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchRealizarPagamentoDTO;
import com.flexpag.paymentscheduler.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    final PagamentoService pagamentoService;

    @PostMapping("/registrar-agendamento")
    public PagamentoPostDTO agendarPagamento(@RequestBody PagamentoModel pagamento){
        return pagamentoService.agendarPagamento(pagamento);
    }

//    @GetMapping("/ver-agendamento")
//    public Optional<PagamentoModel> verAgendamento(@RequestParam Long id){
//        return pagamentoService.verAgendamento(id);
//    }

    @DeleteMapping("excluir-agendamento")
    public void excluirAgendamento(@RequestParam Long id){
        pagamentoService.excluirAgendamento(id);
    }

    @PatchMapping("atualizar-agendamento")
    public PagamentoModel atualizarAgendamento(@RequestParam Long id, @RequestBody PagamentoPatchAtualizarAgendamentoDTO pagamento){
        return pagamentoService.atualizarAgendamento(id, pagamento);
    }

    @PatchMapping("realizar-pagamento")
    public PagamentoModel realizarPagamento(@RequestParam Long id, @RequestBody PagamentoPatchRealizarPagamentoDTO pagamento){
        return pagamentoService.realizarPagamento(id, pagamento);
    }

    @GetMapping("/consultar-status-pagamento")
    public PagamentoGetDTO consultarStatusPagamento(@RequestParam Long id){
        return pagamentoService.consultarStatusAgendamento(id);
    }


}
