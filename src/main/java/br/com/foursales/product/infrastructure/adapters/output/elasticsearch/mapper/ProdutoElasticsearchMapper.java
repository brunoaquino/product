package br.com.foursales.product.infrastructure.adapters.output.elasticsearch.mapper;

import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.entity.ProdutoElasticEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoElasticsearchMapper {

    ProdutoElasticEntity toProdutoEntity(Produto produto);

    Produto toProduto(ProdutoElasticEntity ProdutoEntity);

}