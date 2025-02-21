package br.com.foursales.product.infrastructure.adapters.input.rest.data.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuarioRequest {

    @NotEmpty(message = "Username Não pode ser Vazio")
    @NotBlank(message = "Username não pode ser em branco.")
    private String username;

    @NotEmpty(message = "Senha Não pode ser Vazio")
    @NotBlank(message = "Senha não pode ser em branco.")
    private String password;

}