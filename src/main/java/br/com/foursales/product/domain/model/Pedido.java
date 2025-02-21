package br.com.foursales.product.domain.model;

import br.com.foursales.product.domain.model.tipo.StatusPedido;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;

    private List<ItemPedido> itens;

    private Double valor_total;

    private StatusPedido statusPedido;

    private Date dataCriacaoPedido;

    private Date dataPagamentoPedido;

    private Date dataCancelamentoPedido;

    private Usuario usuario;

}
