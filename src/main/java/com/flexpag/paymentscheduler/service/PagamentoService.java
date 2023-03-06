package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.model.dto.PagamentoGetDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchAtualizarAgendamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchRealizarPagamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPostRetornoDTO;
import com.flexpag.paymentscheduler.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    final PagamentoRepository pagamentoRepository;

    public PagamentoPostRetornoDTO agendarPagamento(PagamentoModel pagamento){
        pagamentoRepository.save(pagamento);
        PagamentoPostRetornoDTO pagamentoDTO = new PagamentoPostRetornoDTO();
        pagamentoDTO.setId(pagamento.getId());
        return pagamentoDTO;
    }

    public void excluirAgendamento(Long id){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        if(pagamentoRegistrado.getStatusPagamento() == StatusPagamento.pending){
            pagamentoRepository.deleteById(id);
        }else{
            throw new NullPointerException();
        }
    }

    public PagamentoModel atualizarAgendamento(Long id, PagamentoPatchAtualizarAgendamentoDTO pagamento){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        if(pagamentoRegistrado.getStatusPagamento() == StatusPagamento.pending){
            BeanUtils.copyProperties(pagamento, pagamentoRegistrado, "id");
            pagamentoRepository.save(pagamentoRegistrado);
        }else{
            throw new NullPointerException();
        }
        return null;
    }

    public PagamentoGetDTO consultarStatusAgendamento(Long id){
        PagamentoGetDTO pagamentoRetornado = new PagamentoGetDTO();
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamentoRegistrado, pagamentoRetornado);
        return pagamentoRetornado;
    }

    public PagamentoModel realizarPagamento(Long id, PagamentoPatchRealizarPagamentoDTO pagamento){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamento, pagamentoRegistrado, "id");
        verificarStatus(pagamentoRegistrado);
        return pagamentoRepository.save(pagamentoRegistrado);
    }

    public StatusPagamento verificarStatus(PagamentoModel pagamento){
        if(pagamento.getSaldoFatura() == pagamento.getValorFatura()){
            pagamento.setStatusPagamento(StatusPagamento.paid);
        }else{
            pagamento.setStatusPagamento(StatusPagamento.pending);
        }
        return pagamento.getStatusPagamento();
    }

}

