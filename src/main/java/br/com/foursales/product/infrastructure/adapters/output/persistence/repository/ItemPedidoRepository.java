package br.com.foursales.product.infrastructure.adapters.output.persistence.repository;

import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ItemPedidoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {

    List<ItemPedidoEntity> findByPedido(PedidoEntity pedido);

}