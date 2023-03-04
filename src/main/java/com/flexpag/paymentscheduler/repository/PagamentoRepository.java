package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.enumaration.StatusPagamento;
import com.flexpag.paymentscheduler.model.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoModel, Long> {
}
