package br.com.foursales.product.application.port.output;

import br.com.foursales.product.domain.model.TicketMedio;

import java.util.List;

public interface CalcularTicketMedioPorUsuario {

    List<TicketMedio> buscarTicketMedioPorUsuario();
}
