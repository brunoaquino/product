package br.com.foursales.product.infrastructure.adapters.input.rest.data.response.produto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCreateResponse {

    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Long quantidadeEmEstoque;

}