package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCreateRequest {

    @NotEmpty(message = "Nome Não pode ser Vazio")
    @NotBlank(message = "Produto não pode ser criado com nome em branco.")
    private String nome;

    @NotEmpty(message = "Categoria Não pode ser Vazio")
    @NotBlank(message = "Produto não pode ser criado com a Categoria em branco.")
    private String categoria;

    private String descricao;

    @NotNull(message = "Campo quantidade não pode ser Vazio")
    private Long quantidadeEmEstoque;

    @NotNull(message = "Campo preço não pode ser Vazio")
    private Double preco;
}