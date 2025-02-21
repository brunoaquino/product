package br.com.foursales.product.infrastructure.adapters.output.elasticsearch;

import br.com.foursales.product.application.port.output.ProdutoOutputElasticsearchPort;
import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.entity.ProdutoElasticEntity;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.mapper.ProdutoElasticsearchMapper;
import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.repository.ProdutoElasticRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProdutoElasticsearchAdapter implements ProdutoOutputElasticsearchPort {

    private final ProdutoElasticRepository produtoRepository;

    private final ProdutoElasticsearchMapper produtoPersistenceMapper;

    @Override
    public void saveProduto(final Produto produto) {
        ProdutoElasticEntity produtoEntity = this.produtoPersistenceMapper.toProdutoEntity(produto);
        produtoEntity = this.produtoRepository.save(produtoEntity);
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return this.produtoPersistenceMapper.toProduto(produtoRepository.findById(String.valueOf(id)).get());
    }

    @Override
    public List<Produto> buscarProdutos(String nome, String categoria, Double precoMin, Double precoMax) {
        return produtoRepository.buscaCustomizada(nome, categoria, precoMin, precoMax).stream().map(this.produtoPersistenceMapper::toProduto).toList();
    }

}