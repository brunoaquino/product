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
public class ProdutoUpdateRequest {

    @NotNull(message = "Campo Id é obrigatório.")
    private Long id;

    @NotEmpty(message = "Nome Não pode ser Vazio.")
    @NotBlank(message = "Produto não pode ser criado com nome em branco.")
    private String nome;

    @NotEmpty(message = "Categoria Não pode ser Vazio.")
    @NotBlank(message = "Produto não pode ser criado com a Categoria em branco.")
    private String categoria;

    private String descricao;

    @NotNull(message = "Campo quantidade é obrigatório.")
    private Long quantidadeEmEstoque;

    @NotNull(message = "Campo preço é obrigatório.")
    private Double preco;
}