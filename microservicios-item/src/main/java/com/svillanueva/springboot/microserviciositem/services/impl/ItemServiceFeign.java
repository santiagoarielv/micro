package com.svillanueva.springboot.microserviciositem.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.svillanueva.springboot.microserviciositem.clientes.ProductoClienteRest;
import com.svillanueva.springboot.microserviciositem.models.Item;com.svillanueva.springboot.app.commons.models.entity.Producto;
import com.svillanueva.springboot.microserviciositem.services.ItemService;

@Service
@Primary
public class ItemServiceFeign implements ItemService {

  @Autowired
  private ProductoClienteRest clienteFeign;

  @Override
  public List<Item> findAll() {

    return clienteFeign.findByAll().stream().map((p) -> new Item(p, 1)).toList();
  }

  @Override
  public Item findById(Long id, Integer cantidad) {
    return new Item(clienteFeign.findById(id), cantidad);
  }

  @Override
  public void deleteById(Long id) {
    clienteFeign.deleteById(id);
  }

  @Override
  public Producto save(Producto producto) {
    return clienteFeign.save(producto);
  }

  @Override
  public Producto update(Producto producto, Long id) {
    return clienteFeign.update(producto, id);
  }

}
