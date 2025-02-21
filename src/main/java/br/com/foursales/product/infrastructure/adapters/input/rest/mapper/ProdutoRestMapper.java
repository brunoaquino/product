package br.com.foursales.product.infrastructure.adapters.input.rest.mapper;

import br.com.foursales.product.domain.model.ConsultaProdutoElastic;
import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto.ConsultaProdutoElasticRequest;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto.ProdutoCreateRequest;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto.ProdutoUpdateRequest;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.response.produto.ProdutoCreateResponse;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.response.produto.ProdutoQueryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoRestMapper {

    Produto toProdutoCreate(ProdutoCreateRequest produtoCreateRequest);

    Produto toProdutoUpdate(ProdutoUpdateRequest produtoUpdateRequest);

    ProdutoCreateResponse toProdutoCreateResponse(Produto produto);

    ConsultaProdutoElastic toConsultaProdutoElasticResponse(ConsultaProdutoElasticRequest consultaProdutoElasticRequest);

    ProdutoQueryResponse toProdutoQueryResponse(Produto produto);

}