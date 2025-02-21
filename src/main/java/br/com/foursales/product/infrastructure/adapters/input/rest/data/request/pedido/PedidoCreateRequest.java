package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.pedido;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoCreateRequest {

    @NotEmpty(message = "Itens do pedido não pode ser vazio.")
    private List<ItemPedidoCreateRequest> itens;

}