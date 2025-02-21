package br.com.foursales.product.infrastructure.adapters.input.eventlistener;

import br.com.foursales.product.domain.event.ProdutoCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProdutoEventListenerAdapter {

    @EventListener
    public void handle(final ProdutoCreatedEvent event){
        log.info("Produto created with id " + event.getId() + " at " + event.getDate());
    }

}