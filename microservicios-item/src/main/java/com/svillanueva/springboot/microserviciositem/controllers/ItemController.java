package com.svillanueva.springboot.microserviciositem.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svillanueva.springboot.microserviciositem.models.Item;
import com.svillanueva.springboot.microserviciositem.models.Producto;
import com.svillanueva.springboot.microserviciositem.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/item")
public class ItemController {

  private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

  @Autowired
  private CircuitBreakerFactory breakerFactory;

  @Autowired
  private ItemService itemService;

  @GetMapping
  public Map<String, Object> findAll() {
    HashMap<String, Object> body = new HashMap<>();
    body.put("items", itemService.findAll());
    return body;
  }

  @GetMapping("/{id}/cantidad/{cantidad}")
  public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
    return breakerFactory.create("items")
        .run(() -> itemService.findById(id, cantidad), (e) -> metodoAlternativo(id, cantidad, e));
  }

  @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
  @GetMapping("/ver/{id}/cantidad/{cantidad}")
  public Item findById2(@PathVariable Long id, @PathVariable Integer cantidad) {
    return itemService.findById(id, cantidad);
  }

  @TimeLimiter(name = "items", fallbackMethod = "metodoAlternativo2")
  @GetMapping("/ver2/{id}/cantidad/{cantidad}")
  public CompletableFuture<Item> findById3(@PathVariable Long id, @PathVariable Integer cantidad) {
    return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
  }

  public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
    logger.info(e.getMessage());
    Item item = new Item();
    Producto producto = new Producto();

    item.setCantidad(cantidad);
    producto.setId(id);
    producto.setNombre("Ejemplo - 1");
    producto.setPrecio(500.00);
    item.setProducto(producto);

    return item;
  }

  public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
    logger.info(e.getMessage());
    Item item = new Item();
    Producto producto = new Producto();

    item.setCantidad(cantidad);
    producto.setId(id);
    producto.setNombre("Ejemplo - 1");
    producto.setPrecio(500.00);
    item.setProducto(producto);

    return CompletableFuture.supplyAsync(() -> item);
  }
}
