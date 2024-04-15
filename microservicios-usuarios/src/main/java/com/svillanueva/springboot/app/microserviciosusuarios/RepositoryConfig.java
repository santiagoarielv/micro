package com.svillanueva.springboot.app.microserviciosusuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.svillanueva.springboot.app.microserviciosusuarios.models.entity.Role;
import com.svillanueva.springboot.app.microserviciosusuarios.models.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(Usuario.class, Role.class);
  }
}
