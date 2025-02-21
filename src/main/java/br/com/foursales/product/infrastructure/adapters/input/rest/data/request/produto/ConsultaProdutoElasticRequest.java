package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaProdutoElasticRequest {

    private String nome;

    private Double maxPreco;

    private Double minPreco;

    private String categoria;
}