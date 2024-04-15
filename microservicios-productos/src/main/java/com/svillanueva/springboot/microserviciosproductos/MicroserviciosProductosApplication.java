package com.svillanueva.springboot.microserviciosproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "com.svillanueva.springboot.app.commons.models.entity" })
public class MicroserviciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosProductosApplication.class, args);
	}

}
