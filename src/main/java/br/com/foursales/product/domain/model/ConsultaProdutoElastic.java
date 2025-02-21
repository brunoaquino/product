package br.com.foursales.product.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaProdutoElastic {

    private String nome;

    private Double maxPreco;

    private Double minPreco;

    private String categoria;
}
