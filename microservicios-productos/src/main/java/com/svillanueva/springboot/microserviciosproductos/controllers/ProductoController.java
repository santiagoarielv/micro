package com.svillanueva.springboot.microserviciosproductos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svillanueva.springboot.microserviciosproductos.models.entity.Producto;
import com.svillanueva.springboot.microserviciosproductos.models.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @GetMapping
  public List<Producto> findAll() {
    return productoService.findAll();
  }

  @GetMapping("/{id}")
  public Producto findById(@PathVariable Long id) {
    return productoService.findById(id);
  }
}
