package br.com.foursales.product.infrastructure.adapters.output.persistence;

import br.com.foursales.product.application.port.output.UsuarioOutputPersistencePort;
import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.UsuarioEntity;
import br.com.foursales.product.infrastructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import br.com.foursales.product.infrastructure.adapters.output.persistence.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioOutputPersistencePort {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioPersistenceMapper usuarioPersistenceMapper;

    @Override
    public Optional<Usuario> findByUsername(String username) {
        final Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findByUsername(username);

        if (usuarioEntity.isEmpty()) {
            return Optional.empty();
        }

        final Usuario usuario = this.usuarioPersistenceMapper.toUsuario(usuarioEntity.get());
        return Optional.of(usuario);
    }
}