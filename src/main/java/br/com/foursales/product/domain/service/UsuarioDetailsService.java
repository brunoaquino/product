package br.com.foursales.product.domain.service;

import br.com.foursales.product.application.port.input.usuario.LoginUsuarioUseCase;
import br.com.foursales.product.application.port.output.UsuarioOutputPersistencePort;
import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.infrastructure.adapters.config.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@AllArgsConstructor
public class UsuarioDetailsService implements UserDetailsService, LoginUsuarioUseCase {

    private final UsuarioOutputPersistencePort usuarioOutputPersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioOutputPersistencePort.findByUsername(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return User.builder()
                .username(usuario.get().getUsername())
                .password(usuario.get().getPassword())
                .roles(usuario.get().getRole().substring(5))
                .build();
    }

    public String login(String username, String password) {
        Optional<Usuario> usuario = usuarioOutputPersistencePort.findByUsername(username);
        if (usuario.isEmpty()) {
            throw new BadCredentialsException("Usuário não encontrado ou credenciais inválidas!");
        }

        if (!passwordEncoder.matches(password, usuario.get().getPassword())) {
            throw new BadCredentialsException("Usuário não encontrado ou credenciais inválidas!");
        }

        return jwtService.gerarToken(usuario.get().getUsername(), usuario.get().getRole());
    }

}
