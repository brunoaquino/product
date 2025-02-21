package br.com.foursales.product.infrastructure.adapters.config;

import br.com.foursales.product.application.port.output.PedidoOutputKafkaPort;
import br.com.foursales.product.application.port.output.PedidoOutputPersistencePort;
import br.com.foursales.product.application.port.output.ProdutoOutputElasticsearchPort;
import br.com.foursales.product.application.port.output.UsuarioOutputPersistencePort;
import br.com.foursales.product.domain.service.PedidoService;
import br.com.foursales.product.infrastructure.adapters.output.kafka.PedidoKafkaAdapter;
import br.com.foursales.product.infrastructure.adapters.output.persistence.PedidoPersistenceAdapter;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.ItemPedidoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.PedidoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.ItemPedidoRepository;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class PedidoBeanConfiguration {

    @Bean
    public PedidoPersistenceAdapter pedidoPersistenceAdapter(final PedidoRepository pedidoRepository, final ItemPedidoRepository itemPedidoRepository, final PedidoPersistenceMapper pedidoPersistenceMapper, final ItemPedidoPersistenceMapper itemPedidoPersistenceMapper, final UsuarioPersistenceMapper usuarioPersistenceMapper) {
        return new PedidoPersistenceAdapter(pedidoRepository, itemPedidoRepository, pedidoPersistenceMapper, itemPedidoPersistenceMapper, usuarioPersistenceMapper);
    }

    @Bean
    public PedidoKafkaAdapter pedidoKafkaAdapter(final KafkaTemplate<String, String> kafkaTemplate) {
        return new PedidoKafkaAdapter(kafkaTemplate);
    }

    @Bean
    public PedidoService pedidoService(final UsuarioOutputPersistencePort usuarioOutputPersistencePort, final PedidoOutputPersistencePort pedidoOutputPersistencePort, final ProdutoOutputElasticsearchPort produtoOutputElasticsearchPort, final PedidoOutputKafkaPort pedidoOutputKafkaPort) {
        return new PedidoService(usuarioOutputPersistencePort, pedidoOutputPersistencePort, produtoOutputElasticsearchPort, pedidoOutputKafkaPort);
    }

}