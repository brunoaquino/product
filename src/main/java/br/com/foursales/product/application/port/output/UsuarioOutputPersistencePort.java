package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioOutputPersistencePort {

    Optional<Usuario> findByUsername(String username);
}
