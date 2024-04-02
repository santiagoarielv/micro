package com.svillanueva.springboot.microserviciositem.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.svillanueva.springboot.microserviciositem.models.Item;
import com.svillanueva.springboot.microserviciositem.models.Producto;
import com.svillanueva.springboot.microserviciositem.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
  @Autowired
  private RestTemplate clienteRest;

  @Override
  public List<Item> findAll() {
    List<Producto> productos = List
        .of(clienteRest.getForObject("http://localhost:8081/api/productos", Producto[].class));

    return productos.stream().map((p) -> new Item(p, 1)).toList();
  }

  @Override
  public Item findById(Long id, Integer cantidad) {
    Map<String, String> pathVariables = new HashMap<>();
    pathVariables.put("id", id.toString());

    Producto producto = clienteRest.getForObject("http://localhost:8081/api/productos/{id}", Producto.class,
        pathVariables);

    return new Item(producto, cantidad);
  }
}
