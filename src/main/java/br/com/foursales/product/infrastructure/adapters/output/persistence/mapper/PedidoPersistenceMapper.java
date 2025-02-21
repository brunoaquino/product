package br.com.foursales.product.infrastructure.adapters.output.persistence.mapper;

import br.com.foursales.product.domain.model.ItemPedido;
import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ItemPedidoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoPersistenceMapper {

    @Mapping(target = "itens", source = "itens", ignore = true)
    PedidoEntity toPedidoEntity(Pedido pedido);

    @Mapping(target = "pedido")
    ItemPedidoEntity toItemEntity(ItemPedido itemPedido);

    @Mapping(target = "itens", source = "itens")
    Pedido toPedido(PedidoEntity PedidoEntity);

    @Mapping(target = "pedido", ignore = true)
    ItemPedido toItemPedido(ItemPedidoEntity itemPedidoEntity);


}