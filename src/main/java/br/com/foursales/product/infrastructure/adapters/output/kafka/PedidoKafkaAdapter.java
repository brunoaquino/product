package br.com.foursales.product.infrastructure.adapters.output.kafka;

import br.com.foursales.product.application.port.output.PedidoOutputKafkaPort;
import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.mapper.ProdutoElasticsearchMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class PedidoKafkaAdapter implements PedidoOutputKafkaPort {

    private static final String TOPICO_ORDER_CREATED = "order.created";
    private static final String TOPICO_ORDER_PAID = "order.paid";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publicarEventoPedidoCriado(Pedido pedido) throws JsonProcessingException {
        kafkaTemplate.send(TOPICO_ORDER_CREATED, new ObjectMapper().writeValueAsString(pedido));
    }

    @Override
    public void publicarEventoPedidoPago(Pedido pedido) throws JsonProcessingException {
        kafkaTemplate.send(TOPICO_ORDER_PAID, new ObjectMapper().writeValueAsString(pedido));
    }
}