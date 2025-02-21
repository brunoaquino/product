package br.com.foursales.product.infrastructure.adapters.input.rest;

import br.com.foursales.product.application.port.input.pedido.CriarPedidoUseCase;
import br.com.foursales.product.application.port.input.pedido.PagarPedidoUseCase;
import br.com.foursales.product.application.port.output.BuscarTop5UsuariosQueMaisCompraram;
import br.com.foursales.product.application.port.output.CalcularFaturamentoMensal;
import br.com.foursales.product.application.port.output.CalcularTicketMedioPorUsuario;
import br.com.foursales.product.domain.model.TicketMedio;
import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.infrastructure.adapters.input.rest.data.request.pedido.PedidoCreateRequest;
import br.com.foursales.product.infrastructure.adapters.input.rest.mapper.PedidoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedido")
@RequiredArgsConstructor
public class PedidoRestAdapter {

    private final PagarPedidoUseCase pagarPedidoUseCase;

    private final BuscarTop5UsuariosQueMaisCompraram buscarTop5UsuariosQueMaisCompraram;

    private final CalcularFaturamentoMensal calcularFaturamentoMensal;

    private final CalcularTicketMedioPorUsuario calcularTicketMedioPorUsuario;

    private final CriarPedidoUseCase criarPedidoUseCase;

    private final PedidoRestMapper pedidoRestMapper;

    @PostMapping(value = "/")
    public ResponseEntity<String> criarPedido(@RequestBody @Valid final PedidoCreateRequest pedidoCreateRequest) {
        Long numPedido = this.criarPedidoUseCase.criar(pedidoRestMapper.toPedido(pedidoCreateRequest));
        return new ResponseEntity<>("Pedido criado com id: " + numPedido, HttpStatus.CREATED);
    }

    @PostMapping(value = "/pagar/{id}")
    public ResponseEntity<String> pagar(@PathVariable final Long id) {
        this.pagarPedidoUseCase.pagar(id);
        return new ResponseEntity<>("Pedido foi pago.", HttpStatus.OK);
    }

    @GetMapping(value = "/buscarTop5UsuariosQueMaisCompraram")
    public ResponseEntity<List<Usuario>> buscarTop5UsuariosQueMaisCompraram() {
        final List<Usuario> usuarios = this.buscarTop5UsuariosQueMaisCompraram.buscar();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/calcularFaturamentoMensal")
    public ResponseEntity<Double> calcularFaturamentoMensal(@RequestParam final int ano, @RequestParam final int mes) {
        final Double faturamento = this.calcularFaturamentoMensal.calcular(ano, mes);
        return new ResponseEntity<>(faturamento, HttpStatus.OK);
    }

    @GetMapping(value = "/calcularTicketMedioPorUsuario")
    public ResponseEntity<List<TicketMedio>> calcularTicketMedioPorUsuario() {
        final List<TicketMedio> tickets = this.calcularTicketMedioPorUsuario.buscarTicketMedioPorUsuario();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}