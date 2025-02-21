package br.com.foursales.product.application.port.input.usuario;

import br.com.foursales.product.domain.model.Usuario;

public interface LoginUsuarioUseCase {
    public String login(String username, String password);
}
