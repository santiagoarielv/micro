package com.svillanueva.springboot.app.gatewayserver.filters.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class FiltroEjemploGatewayFilterFactory
    extends AbstractGatewayFilterFactory<FiltroEjemploGatewayFilterFactory.Configuracion> {

  private final Logger logger = LoggerFactory.getLogger(FiltroEjemploGatewayFilterFactory.class);

  @Override
  public GatewayFilter apply(Configuracion config) {
    return (exchange, chain) -> {
      logger.info(("============= PRE GATEWAY FILTER FACTORY" + config.mensaje));
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        Optional.ofNullable(config.cookieValor)
            .ifPresent((c) -> exchange.getResponse().addCookie(ResponseCookie.from(c).build()));

        logger.info(("============= EJECUTANDO POST GATEWAY FILTER FACTORY" + config.mensaje));
      }));
    };
  }

  public FiltroEjemploGatewayFilterFactory() {
    super(Configuracion.class);
  }

  @Override
  public List<String> shortcutFieldOrder() {
    return Arrays.asList("mensaje", "cookieNombre", "cookieValor");
  }

  @Override
  public String name() {
    return "EjemploCookie";
  }

  public static class Configuracion {
    private String mensaje;
    private String cookieValor;
    private String cookieNombre;

    public String getMensaje() {
      return mensaje;
    }

    public void setMensaje(String mensaje) {
      this.mensaje = mensaje;
    }

    public String getCookieValor() {
      return cookieValor;
    }

    public void setCookieValor(String cookieValor) {
      this.cookieValor = cookieValor;
    }

    public String getCookieNombre() {
      return cookieNombre;
    }

    public void setCookieNombre(String cookieNombre) {
      this.cookieNombre = cookieNombre;
    }
  }
}