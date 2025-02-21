package br.com.foursales.product.infrastructure.adapters.output.eventpublisher;

import br.com.foursales.product.application.port.output.ProdutoEventPublisher;
import br.com.foursales.product.domain.event.ProdutoCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class ProdutoEventPublisherAdapter implements ProdutoEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProdutoCreatedEvent(final ProdutoCreatedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }

}