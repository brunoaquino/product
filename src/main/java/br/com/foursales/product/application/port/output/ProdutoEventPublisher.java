package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.event.ProdutoCreatedEvent;

public interface ProdutoEventPublisher {

    void publishProdutoCreatedEvent(ProdutoCreatedEvent event);
}
