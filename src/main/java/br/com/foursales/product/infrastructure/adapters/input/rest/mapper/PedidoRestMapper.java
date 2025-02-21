package br.com.foursales.product.infrastructure.adapters.input.rest.mapper;

import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.pedido.PedidoCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoRestMapper {

    Pedido toPedido(PedidoCreateRequest pedidoCreateRequest);

}