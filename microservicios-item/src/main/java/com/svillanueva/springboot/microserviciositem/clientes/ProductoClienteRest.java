package com.svillanueva.springboot.microserviciositem.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.svillanueva.springboot.microserviciositem.models.Producto;

@FeignClient(name = "servicio-producto", path = "api/productos")
public interface ProductoClienteRest {
  @GetMapping
  List<Producto> findByAll();

  @GetMapping("/{id}")
  Producto findById(@PathVariable Long id);

  @PostMapping("/crear")
  Producto save(@RequestBody Producto producto);

  @PutMapping("/editar/{id}")
  Producto update(@RequestBody Producto producto, @PathVariable Long id);

  @DeleteMapping("/eliminar/{id}")
  void deleteById(@PathVariable Long id);
}
