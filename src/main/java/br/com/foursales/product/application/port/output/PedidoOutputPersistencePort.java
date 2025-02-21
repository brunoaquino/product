package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.domain.model.TicketMedio;
import br.com.foursales.product.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface PedidoOutputPersistencePort {

    List<Usuario> buscarTop5UsuariosQueMaisCompraram();

    List<TicketMedio> calcularTicketMedioPorUsuario();

    Double calcularFaturamentoMensal(int ano, int mes);

    Pedido savePedido(Pedido pedido);

    Pedido updatePedido(Pedido pedido);

    Optional<Pedido> getPedidoById(Long id);

}
