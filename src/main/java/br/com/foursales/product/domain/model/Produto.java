package br.com.foursales.product.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Long quantidadeEmEstoque;

    private String categoria;
}
