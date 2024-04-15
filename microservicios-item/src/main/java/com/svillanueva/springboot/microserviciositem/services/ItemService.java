package com.svillanueva.springboot.microserviciositem.services;

import java.util.List;

import com.svillanueva.springboot.app.commons.models.entity.Producto;
import com.svillanueva.springboot.microserviciositem.models.Item;

public interface ItemService {

  List<Item> findAll();

  Item findById(Long id, Integer cantidad);

  Producto save(Producto producto);

  Producto update(Producto producto, Long id);

  void deleteById(Long id);
}
