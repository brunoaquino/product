package br.com.foursales.product.domain.event;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCreatedEvent {

    private Long id;

    private LocalDateTime date;

    public ProdutoCreatedEvent(Long id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

}