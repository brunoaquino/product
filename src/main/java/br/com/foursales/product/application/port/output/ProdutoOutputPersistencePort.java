package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.Produto;

import java.util.Optional;

public interface ProdutoOutputPersistencePort {

    Produto saveProduto(Produto produto);

    Optional<Produto> getProdutoById(Long id);

    Optional<Produto> getProdutoByNome(String nome);
}
