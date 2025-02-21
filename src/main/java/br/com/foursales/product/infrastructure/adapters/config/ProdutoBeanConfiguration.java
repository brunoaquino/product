package br.com.foursales.product.infrastructure.adapters.config;

import br.com.foursales.product.application.port.output.ProdutoOutputElasticsearchPort;
import br.com.foursales.product.domain.service.ProdutoService;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.ProdutoElasticsearchAdapter;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.mapper.ProdutoElasticsearchMapper;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.repository.ProdutoElasticRepository;
import br.com.foursales.product.infrastructure.adapters.output.eventpublisher.ProdutoEventPublisherAdapter;
import br.com.foursales.product.infrastructure.adapters.output.persistence.ProdutoPersistenceAdapter;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.ProdutoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.ProdutoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguration {

    @Bean
    public ProdutoPersistenceAdapter produtoPersistenceAdapter(final ProdutoRepository produtoRepository, final ProdutoPersistenceMapper produtoPersistenceMapper) {
        return new ProdutoPersistenceAdapter(produtoRepository, produtoPersistenceMapper);
    }
    @Bean
    public ProdutoElasticsearchAdapter produtoElasticsearchAdapter(final ProdutoElasticRepository produtoElasticRepository, final ProdutoElasticsearchMapper produtoElasticsearchMapper) {
        return new ProdutoElasticsearchAdapter(produtoElasticRepository, produtoElasticsearchMapper);
    }

    @Bean
    public ProdutoEventPublisherAdapter produtoEventPublisherAdapter(final ApplicationEventPublisher applicationEventPublisher) {
        return new ProdutoEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public ProdutoService produtoService(final ProdutoPersistenceAdapter produtoPersistenceAdapter, final ProdutoEventPublisherAdapter produtoEventPublisherAdapter, final ProdutoOutputElasticsearchPort produtoOutputElasticsearchPort) {
        return new ProdutoService(produtoPersistenceAdapter, produtoEventPublisherAdapter, produtoOutputElasticsearchPort);
    }

}