package br.com.foursales.product.infrastructure.adapters.output.persistence.mapper;

import br.com.foursales.product.domain.model.Usuario;
import br.com.foursales.product.infrastructure.adapters.output.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioPersistenceMapper {

    UsuarioEntity toUsuarioEntity(Usuario usuario);

    Usuario toUsuario(UsuarioEntity UsuarioEntity);

}