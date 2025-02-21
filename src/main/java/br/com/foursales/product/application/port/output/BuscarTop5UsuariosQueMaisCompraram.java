package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.Usuario;

import java.util.List;

public interface BuscarTop5UsuariosQueMaisCompraram {

    List<Usuario> buscar();
}
