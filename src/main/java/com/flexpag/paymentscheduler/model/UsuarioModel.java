package com.flexpag.paymentscheduler.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nome;
    private String cpf;
    private double saldoUsuarioConta;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PagamentoModel> pagamentos;
}
