package br.com.foursales.product.infrastructure.adapters.output.persistence;

import br.com.foursales.product.application.port.output.ProdutoOutputPersistencePort;
import br.com.foursales.product.domain.exception.ProdutoException;
import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ProdutoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.ProdutoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.ItemPedidoRepository;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProdutoPersistenceAdapter implements ProdutoOutputPersistencePort {

    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    private final ProdutoPersistenceMapper produtoPersistenceMapper;

    @Override
    public Produto saveProduto(final Produto produto) {
        ProdutoEntity produtoEntity = this.produtoPersistenceMapper.toProdutoEntity(produto);
        produtoEntity = this.produtoRepository.save(produtoEntity);
        return this.produtoPersistenceMapper.toProduto(produtoEntity);
    }

    @Override
    public Optional<Produto> getProdutoById(final Long id) {
        final Optional<ProdutoEntity> produtoEntity = this.produtoRepository.findById(id);

        if (produtoEntity.isEmpty()) {
            return Optional.empty();
        }

        final Produto produto = this.produtoPersistenceMapper.toProduto(produtoEntity.get());
        return Optional.of(produto);
    }

    @Override
    public void delete(Long id) {
        ProdutoEntity produto = this.produtoRepository.findById(id).get();
        if (!this.itemPedidoRepository.findByProduto(produto).isEmpty()) {
            throw new ProdutoException("Produto Não pode ser deletado, pois está sendo usado.");
        }
        this.produtoRepository.delete(produto);
    }

    @Override
    public Optional<Produto> getProdutoByNome(String nome) {
        final Optional<ProdutoEntity> produtoEntity = this.produtoRepository.getProdutoByNome(nome);

        if (produtoEntity.isEmpty()) {
            return Optional.empty();
        }

        final Produto produto = this.produtoPersistenceMapper.toProduto(produtoEntity.get());
        return Optional.of(produto);
    }

}