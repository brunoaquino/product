package br.com.foursales.product.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;

    private String username;

    private String password;

    private String role;
}
