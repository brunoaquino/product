package br.com.foursales.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketMedio {

    private Long usuarioId;
    private Double ticketMedio;
}
