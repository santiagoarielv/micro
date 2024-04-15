package com.svillanueva.springboot.microserviciosproductos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.svillanueva.springboot.microserviciosproductos.models.entity.Producto;
import com.svillanueva.springboot.microserviciosproductos.models.services.ProductoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
  @Value("${server.port}")
  private String port;

  @Autowired
  private ProductoService productoService;

  @GetMapping
  public List<Producto> findAll() {
    return productoService.findAll().stream().map(e -> {
      e.setPort(Integer.parseInt(port));
      return e;
    }).toList();
  }

  @GetMapping("/{id}")
  public Producto findById(@PathVariable Long id) throws InterruptedException {
    if (id.equals(10L)) {
      throw new IllegalStateException("Producto no encontrado");
    } else if (id.equals(7L)) {
      TimeUnit.SECONDS.sleep(5L);
    }

    Producto producto = productoService.findById(id);
    producto.setPort(Integer.parseInt(port));
    return producto;
  }

  @PostMapping("/crear")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto postMethodName(@RequestBody Producto producto) {
    return productoService.save(producto);
  }

  @PostMapping("/editar/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto postMethodName(@RequestBody Producto producto, @PathVariable Long id) {
    Producto productoDb = productoService.findById(id);
    productoDb.setNombre(producto.getNombre());
    productoDb.setPrecio(producto.getPrecio());
    return productoService.save(productoDb);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    productoService.deleteById(id);
  }
}
