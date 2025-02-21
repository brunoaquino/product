package br.com.foursales.product.domain.service;

import br.com.foursales.product.application.port.input.pedido.CriarPedidoUseCase;
import br.com.foursales.product.application.port.input.pedido.PagarPedidoUseCase;
import br.com.foursales.product.application.port.output.*;
import br.com.foursales.product.domain.exception.PedidoException;
import br.com.foursales.product.domain.exception.ProdutoException;
import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.domain.model.Produto;
import br.com.foursales.product.domain.model.TicketMedio;
import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.domain.model.tipo.StatusPedido;
import br.com.foursales.product.domain.util.UsuarioLogadoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class PedidoService implements CriarPedidoUseCase, PagarPedidoUseCase, BuscarTop5UsuariosQueMaisCompraram, CalcularFaturamentoMensal, CalcularTicketMedioPorUsuario {

    private final UsuarioOutputPersistencePort usuarioOutputPersistencePort;
    private final PedidoOutputPersistencePort pedidoOutputPersistencePort;
    private final ProdutoOutputElasticsearchPort produtoOutputElasticsearchPort;
    private final PedidoOutputKafkaPort pedidoOutputKafkaPort;

    @Override
    public Long criar(Pedido pedido) {
        pedido.setUsuario(usuarioOutputPersistencePort.findByUsername(UsuarioLogadoUtil.getUsuarioLogado()).get());
        pedido.setDataCriacaoPedido(Calendar.getInstance().getTime());
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        Pedido finalPedido = pedido;
        Double precoTotalPedido = pedido.getItens()
                .stream()
                .mapToDouble(item -> {
                    Produto produto = produtoOutputElasticsearchPort.buscarProdutoPorId(item.getProduto().getId());
                    if (produto == null) {
                        throw new ProdutoException(
                                "Produto com ID " + item.getProduto().getId() + " não foi encontrado. Pedido cancelado."
                        );
                    } else if (produto.getQuantidadeEmEstoque() < item.getQuantidade()) {
                        throw new ProdutoException(
                                "Produto " + produto.getNome() + " com ID " + item.getProduto().getId() + " Não possui estoque suficiente. Pedido cancelado."
                        );
                    }

                    item.setPedido(finalPedido);
                    item.setProduto(produto);
                    return produto.getPreco() * item.getQuantidade();
                }).sum();

        pedido.setValor_total(precoTotalPedido);


        pedido = this.pedidoOutputPersistencePort.savePedido(pedido);

        try {
            pedidoOutputKafkaPort.publicarEventoPedidoCriado(pedido);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao enviar pedido para a Fila de pedido criado.");
        }

        return pedido.getId();
    }

    @Override
    public Pedido pagar(Long id) {
        Optional<Pedido> pedido = pedidoOutputPersistencePort.getPedidoById(id);
        if (pedido.isEmpty()) {
            throw new PedidoException("Pedido não encontrado com id " + id);
        }
        if (pedido.get().getStatusPedido() == StatusPedido.CONCLUIDO) {
            throw new PedidoException("Pedido não pode ser pago pois está com status: " + pedido.get().getStatusPedido());
        }
        pedido.get().setDataPagamentoPedido(Calendar.getInstance().getTime());
        pedido.get().setStatusPedido(StatusPedido.CONCLUIDO);

        Pedido pedidoPago = pedidoOutputPersistencePort.updatePedido(pedido.get());

        try {
            pedidoOutputKafkaPort.publicarEventoPedidoPago(pedidoPago);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao enviar pedido para a Fila de pedido pago.");
        }

        return pedidoPago;
    }

    @Override
    public List<Usuario> buscar() {
        return pedidoOutputPersistencePort.buscarTop5UsuariosQueMaisCompraram();
    }

    @Override
    public Double calcular(int ano, int mes) {
        return pedidoOutputPersistencePort.calcularFaturamentoMensal(ano, mes);
    }

    @Override
    public List<TicketMedio> buscarTicketMedioPorUsuario() {
        return pedidoOutputPersistencePort.calcularTicketMedioPorUsuario();
    }
}