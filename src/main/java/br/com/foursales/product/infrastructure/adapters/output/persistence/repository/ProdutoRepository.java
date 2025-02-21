package br.com.foursales.product.infrastructure.adapters.output.persistence.repository;

import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    Optional<ProdutoEntity> getProdutoByNome(String nome);

}