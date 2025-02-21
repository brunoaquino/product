package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.pedido;

import br.com.foursales.product.domain.model.Produto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoItemPedidoCreateRequest {

    private Long id;

}