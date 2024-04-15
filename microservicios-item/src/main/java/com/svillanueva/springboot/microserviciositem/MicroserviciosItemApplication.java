package com.svillanueva.springboot.microserviciositem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan({ "com.svillanueva.springboot.app.commons.models.entity" })
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class MicroserviciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosItemApplication.class, args);
	}
}
