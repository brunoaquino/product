package br.com.foursales.product.domain.service;

import br.com.foursales.product.application.port.input.produto.*;
import br.com.foursales.product.application.port.output.ProdutoEventPublisher;
import br.com.foursales.product.application.port.output.ProdutoOutputElasticsearchPort;
import br.com.foursales.product.application.port.output.ProdutoOutputPersistencePort;
import br.com.foursales.product.domain.event.ProdutoCreatedEvent;
import br.com.foursales.product.domain.exception.ProdutoException;
import br.com.foursales.product.domain.model.Produto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProdutoService implements CreateProdutoUseCase, GetProdutoUseCase, SaveProdutoElasticsearchUseCase, UpdateProdutoUseCase, DeleteProdutoUseCase {

    private final ProdutoOutputPersistencePort produtoOutputPort;

    private final ProdutoEventPublisher produtoEventPublisher;

    private final ProdutoOutputElasticsearchPort produtoOutputElasticsearchPort;

    @Override
    public Produto createProduto(Produto produto) {

        if (this.produtoOutputPort.getProdutoByNome(produto.getNome()).isPresent()) {
            throw new ProdutoException("Produto Já existe com esse nome");
        }

        produto = this.produtoOutputPort.saveProduto(produto);

        this.produtoOutputElasticsearchPort.saveProduto(produto);

        this.produtoEventPublisher.publishProdutoCreatedEvent(new ProdutoCreatedEvent(produto.getId()));
        return produto;
    }

    @Override
    public Produto getProdutoById(final Long id) {
        return this.produtoOutputPort.getProdutoById(id).orElseThrow(() -> new ProdutoException("Produto não encontrado com id " + id));
    }

    @Override
    public List<Produto> geProdutosElastic(String nome, String categoria, Double precoMin, Double precoMax) {
        return produtoOutputElasticsearchPort.buscarProdutos(nome, categoria, precoMin, precoMax);
    }

    @Override
    public void saveElasticsearcProduto(Long idProduto) {
        this.produtoOutputElasticsearchPort.saveProduto(this.produtoOutputPort.getProdutoById(idProduto).get());
    }

    @Override
    public Produto updateProduto(Produto produto) {
        this.produtoOutputPort.saveProduto(produto);
        this.produtoOutputElasticsearchPort.saveProduto(produto);
        return produto;
    }

    @Override
    public void delete(Long id) {
        this.produtoOutputPort.delete(id);
    }
}