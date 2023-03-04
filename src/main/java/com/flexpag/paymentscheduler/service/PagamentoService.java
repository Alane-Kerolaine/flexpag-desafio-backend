package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import com.flexpag.paymentscheduler.model.PagamentoDTO;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import com.flexpag.paymentscheduler.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
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

    public PagamentoModel atualizarAgendamento(PagamentoModel pagamento){
        return pagamentoRepository.save(pagamento);
    }

}

