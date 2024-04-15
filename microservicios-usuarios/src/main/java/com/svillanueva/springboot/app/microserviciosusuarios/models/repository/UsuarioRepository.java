package com.svillanueva.springboot.app.microserviciosusuarios.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.svillanueva.springboot.app.usuarioscommons.models.entity.Usuario;

@RepositoryRestResource(path = "api/usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
  public Usuario findByUsername(String username);
}
