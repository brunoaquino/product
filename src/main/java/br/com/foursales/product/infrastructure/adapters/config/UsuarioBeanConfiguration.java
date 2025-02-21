package br.com.foursales.product.infrastructure.adapters.config;

import br.com.foursales.product.domain.service.UsuarioDetailsService;
import br.com.foursales.product.infrastructure.adapters.config.security.JwtService;
import br.com.foursales.product.infrastructure.adapters.output.persistence.UsuarioPersistenceAdapter;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UsuarioBeanConfiguration {

    @Bean
    public UsuarioPersistenceAdapter usuarioPersistenceAdapter(final UsuarioRepository usuarioRepository, final UsuarioPersistenceMapper usuarioPersistenceMapper) {
        return new UsuarioPersistenceAdapter(usuarioRepository, usuarioPersistenceMapper);
    }

    @Bean
    public UsuarioDetailsService usuarioService(final UsuarioPersistenceAdapter usuarioPersistenceAdapter, final BCryptPasswordEncoder bCryptPasswordEncoder, final JwtService jwtService) {
        return new UsuarioDetailsService(usuarioPersistenceAdapter, bCryptPasswordEncoder, jwtService);
    }

}