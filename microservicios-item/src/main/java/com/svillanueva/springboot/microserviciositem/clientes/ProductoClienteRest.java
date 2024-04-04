package com.svillanueva.springboot.microserviciositem.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.svillanueva.springboot.microserviciositem.models.Producto;

@FeignClient(name = "servicio-producto", url = "localhost:8081", path = "api/productos")
public interface ProductoClienteRest {
  @GetMapping
  List<Producto> findByAll();

  @GetMapping("/{id}")
  Producto findById(@PathVariable Long id);
}
