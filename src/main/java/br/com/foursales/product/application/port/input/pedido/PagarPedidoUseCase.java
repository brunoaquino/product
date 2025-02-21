package br.com.foursales.product.application.port.input.pedido;

import br.com.foursales.product.domain.model.Pedido;

public interface PagarPedidoUseCase {
    Pedido pagar(Long id);
}
