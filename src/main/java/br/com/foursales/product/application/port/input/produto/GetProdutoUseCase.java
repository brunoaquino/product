package br.com.foursales.product.application.port.input.produto;

import br.com.foursales.product.domain.model.ConsultaProdutoElastic;
import br.com.foursales.product.domain.model.Produto;

import java.util.List;

public interface GetProdutoUseCase {
    Produto getProdutoById(Long id);

    List<Produto> geProdutosElastic(String nome, String categoria, Double precoMin, Double precoMax);
}
