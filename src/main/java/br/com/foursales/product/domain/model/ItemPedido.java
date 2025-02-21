package br.com.foursales.product.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedido {

    private Long id;

    private Pedido pedido;

    private Produto produto;

    private Integer quantidade;
}
