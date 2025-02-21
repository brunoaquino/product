package br.com.foursales.product.infrastructure.adapters.output.persistence.entity;

import br.com.foursales.product.domain.model.tipo.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoEntity> itens;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    private Date dataCriacaoPedido;

    private Date dataPagamentoPedido;

    private Date dataCancelamentoPedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    private Double valor_total;


}