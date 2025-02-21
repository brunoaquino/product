package br.com.foursales.product.application.port.input.produto;

import br.com.foursales.product.domain.model.Produto;

public interface SaveProdutoElasticsearchUseCase {
    void saveElasticsearcProduto(Long idProduto);
}
