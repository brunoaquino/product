package br.com.foursales.product.infrastructure.adapters.output.elasticsearch.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "produtos")
public class ProdutoElasticEntity {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String nome;

    @Field(type = FieldType.Text)
    private String descricao;

    @Field(type = FieldType.Double)
    private Double preco;

    @Field(type = FieldType.Integer)
    private Long quantidadeEmEstoque;

    @Field(type = FieldType.Text)
    private String categoria;

}