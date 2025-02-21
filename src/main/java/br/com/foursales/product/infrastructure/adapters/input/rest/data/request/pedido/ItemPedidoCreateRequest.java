package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.pedido;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoCreateRequest {

    private ProdutoItemPedidoCreateRequest produto;

    private Integer quantidade;

}