package br.com.foursales.product.infrastructure.adapters.output.persistence;

import br.com.foursales.product.application.port.output.PedidoOutputPersistencePort;
import br.com.foursales.product.domain.model.ItemPedido;
import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.domain.model.TicketMedio;
import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.ItemPedidoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.PedidoEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.ItemPedidoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.PedidoPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.ItemPedidoRepository;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PedidoPersistenceAdapter implements PedidoOutputPersistencePort {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    private final PedidoPersistenceMapper pedidoPersistenceMapper;
    private final ItemPedidoPersistenceMapper itemPedidoPersistenceMapper;
    private final UsuarioPersistenceMapper usuarioPersistenceMapper;

    @Override
    public List<Usuario> buscarTop5UsuariosQueMaisCompraram() {
        return pedidoRepository.buscarTop5UsuariosQueMaisCompraram().stream().map(usuarioPersistenceMapper::toUsuario).toList();
    }

    @Override
    public List<TicketMedio> calcularTicketMedioPorUsuario() {
        return pedidoRepository.calcularTicketMedioPorUsuario();
    }

    @Override
    public Double calcularFaturamentoMensal(int ano, int mes) {
        YearMonth anoMes = YearMonth.of(ano, mes);
        LocalDateTime inicioMes = anoMes.atDay(1).atStartOfDay();
        LocalDateTime fimMes = anoMes.atEndOfMonth().atTime(23, 59, 59);

        return pedidoRepository.calcularFaturamentoMensal(inicioMes, fimMes);
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = this.pedidoPersistenceMapper.toPedidoEntity(pedido);

        List<ItemPedido> itensPedidos = pedido.getItens();

        pedidoEntity = this.pedidoRepository.save(pedidoEntity);
        pedido = this.pedidoPersistenceMapper.toPedido(pedidoEntity);

        pedido.setItens(this.salvarItemPedido(itensPedidos, pedido.getId()).stream().map(itemPedidoPersistenceMapper::toItemPedido).toList());

        return pedido;
    }

    @Override
    public Pedido updatePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = this.pedidoPersistenceMapper.toPedidoEntity(pedido);
        pedidoEntity.setItens(itemPedidoRepository.findByPedido(pedidoEntity));

        pedidoEntity = this.pedidoRepository.save(pedidoEntity);

        pedido.setItens(pedidoEntity.getItens().stream().map(itemPedidoPersistenceMapper::toItemPedido).toList());

        return pedido;
    }

    private List<ItemPedidoEntity> salvarItemPedido(List<ItemPedido> itensPedidos, Long pedidoId) {
        return itensPedidos.stream().map(item -> {
            item.getPedido().setId(pedidoId);
            return itemPedidoRepository.save(itemPedidoPersistenceMapper.toItemPedidoEntity(item));
        }).toList();
    }

    @Override
    public Optional<Pedido> getPedidoById(Long id) {
        final Optional<PedidoEntity> pedidoEntity = this.pedidoRepository.findById(id);

        if (pedidoEntity.isEmpty()) {
            return Optional.empty();
        }

        final Pedido pedido = this.pedidoPersistenceMapper.toPedido(pedidoEntity.get());
        pedido.setItens(this.itemPedidoRepository.findByPedido(pedidoEntity.get()).stream().map(itemPedidoPersistenceMapper::toItemPedido).toList());
        return Optional.of(pedido);
    }
}