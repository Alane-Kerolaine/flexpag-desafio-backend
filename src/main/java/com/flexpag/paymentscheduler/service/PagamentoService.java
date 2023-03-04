package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchAtualizarAgendamentoDTO;
import com.flexpag.paymentscheduler.model.dto.PagamentoPatchRealizarPagamentoDTO;
import com.flexpag.paymentscheduler.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    final PagamentoRepository pagamentoRepository;

    public PagamentoModel agendarPagamento(PagamentoModel pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public Optional<PagamentoModel> verAgendamento(Long id){
        return pagamentoRepository.findById(id);
    }

    public void excluirAgendamento(Long id){
        pagamentoRepository.deleteById(id);
    }

    public PagamentoModel atualizarAgendamento(Long id, PagamentoPatchAtualizarAgendamentoDTO pagamento){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamento, pagamentoRegistrado, "id");
        return pagamentoRepository.save(pagamentoRegistrado);
    }

    public PagamentoModel realizarPagamento(Long id, PagamentoPatchRealizarPagamentoDTO pagamento){
        PagamentoModel pagamentoRegistrado = pagamentoRepository.findById(id).get();
        BeanUtils.copyProperties(pagamento, pagamentoRegistrado, "id");
        return pagamentoRepository.save(pagamentoRegistrado);
    }

}

