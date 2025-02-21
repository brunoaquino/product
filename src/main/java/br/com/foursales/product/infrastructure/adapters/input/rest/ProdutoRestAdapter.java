package br.com.foursales.product.infrastructure.adapters.input.rest;

import br.com.foursales.product.application.port.input.produto.CreateProdutoUseCase;
import br.com.foursales.product.application.port.input.produto.GetProdutoUseCase;
import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.produto.ProdutoCreateRequest;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.response.produto.ProdutoCreateResponse;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.response.produto.ProdutoQueryResponse;
import br.com.foursales.product.infrastructure.adapters.input.rest.mapper.ProdutoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoRestAdapter {

    private final CreateProdutoUseCase createProdutoUseCase;

    private final GetProdutoUseCase getProdutoUseCase;

    private final ProdutoRestMapper produtoRestMapper;

    @PostMapping(value = "/")
    public ResponseEntity<ProdutoCreateResponse> createProduto(@RequestBody @Valid final ProdutoCreateRequest produtoCreateRequest) {
        Produto produto = this.produtoRestMapper.toProduto(produtoCreateRequest);

        produto = this.createProdutoUseCase.createProduto(produto);

        return new ResponseEntity<>(this.produtoRestMapper.toProdutoCreateResponse(produto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoQueryResponse> getProduct(@PathVariable final Long id) {
        final Produto produto = this.getProdutoUseCase.getProdutoById(id);
        return new ResponseEntity<>(this.produtoRestMapper.toProdutoQueryResponse(produto), HttpStatus.OK);
    }

    @GetMapping(value = "/filtros")
    public ResponseEntity<List<ProdutoQueryResponse>> buscarProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax
    ) {
        String nomeFiltro = (nome != null && !nome.isEmpty()) ? nome : "*";
        String categoriaFiltro = (categoria != null && !categoria.isEmpty()) ? categoria : "*";
        Double precoMinFiltro = (precoMin != null) ? precoMin : 0.0;
        Double precoMaxFiltro = (precoMax != null) ? precoMax : Double.MAX_VALUE;

        return new ResponseEntity<>(this.getProdutoUseCase.geProdutosElastic(nomeFiltro, categoriaFiltro, precoMinFiltro, precoMaxFiltro).stream().map(produtoRestMapper::toProdutoQueryResponse).toList(), HttpStatus.OK);
    }

}