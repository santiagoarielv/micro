package com.svillanueva.springboot.microserviciositem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "servicio-producto")
public class MicroserviciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosItemApplication.class, args);
	}
}
