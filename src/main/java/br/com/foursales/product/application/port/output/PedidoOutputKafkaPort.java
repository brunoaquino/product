package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PedidoOutputKafkaPort {
    public void publicarEventoPedidoCriado(Pedido pedido) throws JsonProcessingException;

    public void publicarEventoPedidoPago(Pedido pedido) throws JsonProcessingException;
}
