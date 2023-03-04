package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.model.UsuarioModel;
import com.flexpag.paymentscheduler.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;

    @PostMapping("/cadastrar-usuario")
    public UsuarioModel cadastrarUsuario(@RequestBody UsuarioModel usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

}
