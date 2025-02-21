package br.com.foursales.product.infrastructure.adapters.output.persistence.mapper;

import br.com.foursales.product.domain.model.ItemPedido;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ItemPedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoPersistenceMapper {

    ItemPedidoEntity toItemPedidoEntity(ItemPedido itemPedido);

    ItemPedido toItemPedido(ItemPedidoEntity ItemPedidoEntity);

}