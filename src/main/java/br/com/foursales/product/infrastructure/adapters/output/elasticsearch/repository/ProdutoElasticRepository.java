package br.com.foursales.product.infrastructure.adapters.output.elasticsearch.repository;

import br.com.foursales.product.infrastructure.adapters.output.elasticsearch.entity.ProdutoElasticEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoElasticRepository extends ElasticsearchRepository<ProdutoElasticEntity, String> {

    @Query("""
                {
                  "bool": {
                    "should": [
                      { "match": { "nome": "?0" } },
                      { "match": { "categoria": "?1" } }
                    ],
                    "must": [
                      { "range": { "preco": { "gte": "?2", "lte": "?3" } } },
                      { "range": { "quantidadeEmEstoque": { "gt": 0 } } }
                    ],
                    "minimum_should_match": 1
                  }
                }
            """)
    List<ProdutoElasticEntity> buscaCustomizada(String nome, String categoria, Double precoMin, Double precoMax);

}