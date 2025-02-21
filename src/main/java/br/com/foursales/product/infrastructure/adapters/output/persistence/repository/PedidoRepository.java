package br.com.foursales.product.infrastructure.adapters.output.persistence.repository;

import br.com.foursales.product.domain.model.TicketMedio;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p.usuario AS usuario "
            + "FROM PedidoEntity p "
            + "GROUP BY p.usuario "
            + "ORDER BY COUNT(p) DESC")
    List<UsuarioEntity> buscarTop5UsuariosQueMaisCompraram();

    @Query("SELECT new br.com.foursales.product.domain.model.TicketMedio(p.usuario.id, AVG(p.valor_total)) " +
            "FROM PedidoEntity p " +
            "GROUP BY p.usuario.id " +
            "ORDER BY p.usuario.id")
    List<TicketMedio> calcularTicketMedioPorUsuario();

    @Query("SELECT SUM(p.valor_total) " +
            "FROM PedidoEntity p " +
            "WHERE p.statusPedido = 'CONCLUIDO' " +
            "AND p.dataCriacaoPedido BETWEEN :inicioMes AND :fimMes")
    Double calcularFaturamentoMensal(@Param("inicioMes") LocalDateTime inicioMes,
                                     @Param("fimMes") LocalDateTime fimMes);


}