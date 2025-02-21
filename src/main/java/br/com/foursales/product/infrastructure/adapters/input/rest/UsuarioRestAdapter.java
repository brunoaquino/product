package br.com.foursales.product.infrastructure.adapters.input.rest;

import br.com.foursales.product.application.port.input.usuario.LoginUsuarioUseCase;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.usuario.LoginUsuarioRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class UsuarioRestAdapter {

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    @PostMapping(value = "/login")
    public String login(@RequestBody @Valid final LoginUsuarioRequest usuario) {
        return loginUsuarioUseCase.login(usuario.getUsername(), usuario.getPassword());
    }

}