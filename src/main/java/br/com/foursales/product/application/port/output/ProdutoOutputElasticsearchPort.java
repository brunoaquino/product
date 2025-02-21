package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.ConsultaProdutoElastic;
import br.com.foursales.product.domain.model.Produto;

import java.util.List;

public interface ProdutoOutputElasticsearchPort {

    void saveProduto(Produto produto);

    Produto buscarProdutoPorId(Long id);

    List<Produto> buscarProdutos(String nome, String categoria, Double precoMin, Double precoMax);
}
