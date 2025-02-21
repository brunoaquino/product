package br.com.foursales.product.infrastructure.adapters.output.persistence.mapper;

import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoPersistenceMapper {

    ProdutoEntity toProdutoEntity(Produto produto);

    Produto toProduto(ProdutoEntity ProdutoEntity);

}