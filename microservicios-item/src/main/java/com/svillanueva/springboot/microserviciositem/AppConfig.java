package com.svillanueva.springboot.microserviciositem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  RestTemplate registrarRestTemplate() {
    return new RestTemplate();
  }
}
