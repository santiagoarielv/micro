package com.svillanueva.springboot.microserviciositem.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.svillanueva.springboot.microserviciositem.models.Item;com.svillanueva.springboot.app.commons.models.entity.Producto;
import com.svillanueva.springboot.microserviciositem.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RefreshScope
@RestController
@RequestMapping("/api/item")
public class ItemController {
  private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

  @Autowired
  private Environment env;

  @Value("${configuracion.texto}")
  private String texto;

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

  @GetMapping("/obtener-config")
  public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
    logger.info(texto);
    Map<String, String> json = new HashMap<>();
    json.put("texto", texto);
    json.put("puerto", puerto);

    if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
      // json.put("autor.nombre", puerto)
    }

    return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
  }

  @PostMapping("/crear")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto postMethodName(@RequestBody Producto producto) {
    return itemService.save(producto);
  }

  @PutMapping("/editar/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto putMethodName(@RequestBody Producto producto, @PathVariable Long id) {
    return itemService.update(producto, id);
  }

  @DeleteMapping("/eliminar/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMethodName(@PathVariable Long id) {
    itemService.deleteById(id);
  }
}
