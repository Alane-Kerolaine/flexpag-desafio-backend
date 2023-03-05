package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.model.dto.PagamentoGetDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchAtualizarAgendamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchRealizarPagamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPostDTO;
import com.flexpag.paymentscheduler.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagamentoService {

    final PagamentoRepository pagamentoRepository;

    public PagamentoPostDTO agendarPagamento(PagamentoModel pagamento){
        pagamentoRepository.save(pagamento);
        PagamentoPostDTO pagamentoDTO = new PagamentoPostDTO();
        pagamentoDTO.setId(pagamento.getId());
        return pagamentoDTO;
    }

//    public Optional<PagamentoModel> verAgendamento(Long id){
//        return pagamentoRepository.findById(id);
//    }

    public void excluirAgendamento(Long id){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        verificarStatus(pagamentoRegistrado);
        if(pagamentoRegistrado.getStatusPagamento() == StatusPagamento.pending){
            pagamentoRepository.deleteById(id);
        }else{
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    public PagamentoModel atualizarAgendamento(Long id, PagamentoPatchAtualizarAgendamentoDTO pagamento){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamento, pagamentoRegistrado, "id");
        return pagamentoRepository.save(pagamentoRegistrado);
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

    public PagamentoGetDTO consultarStatusAgendamento(Long id){
        PagamentoGetDTO pagamentoRetornado = new PagamentoGetDTO();
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamentoRegistrado, pagamentoRetornado);
        return pagamentoRetornado;
    }

}

