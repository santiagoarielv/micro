package com.svillanueva.springboot.app.gatewayserver.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class EjemploGlobalFilter implements GlobalFilter, Ordered {

  private final Logger logger = LoggerFactory.getLogger(EjemploGlobalFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    logger.info("========== PRE FILTRO ==========");
    exchange.getRequest().mutate().headers(http -> http.add("token", "12345"));

    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
      logger.info("========== POST FILTRO ==========");

      Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent((v -> {
        exchange.getResponse().getHeaders().add("token", v);
      }));

      exchange.getResponse().getCookies().add("color", ResponseCookie.from("verde").build());
      // exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    }));
  }

  @Override
  public int getOrder() {
    return 1;
  }

}
