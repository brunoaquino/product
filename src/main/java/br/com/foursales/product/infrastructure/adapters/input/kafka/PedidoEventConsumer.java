package br.com.foursales.product.infrastructure.adapters.input.kafka;

import br.com.foursales.product.application.port.input.produto.UpdateProdutoUseCase;
import br.com.foursales.product.domain.model.Pedido;
import br.com.foursales.product.domain.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PedidoEventConsumer {

    private final UpdateProdutoUseCase updateProdutoUseCase;

    @KafkaListener(topics = "order.paid", groupId = "stock-update-group")
    public void consumirEventoPedidoPago(String mensagem) {
        try {
            atualizarEstoque(mensagem);
        } catch (Exception e) {
            log.error("Erro ao atualizar o estoque: " + e.getMessage());
        }
    }

    private void atualizarEstoque(String mensagem) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Pedido pedido = objectMapper.readValue(mensagem, Pedido.class);
            pedido.getItens().forEach(item -> {
                Produto produto = item.getProduto();
                produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidade());
                if (produto.getQuantidadeEmEstoque() < 0) {
                    throw new RuntimeException("Sem stock disponivel para o produto: " + produto.getNome());
                }
                updateProdutoUseCase.updateProduto(produto);
            });

            log.info(pedido.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
