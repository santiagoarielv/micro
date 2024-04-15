package com.svillanueva.springboot.microserviciosproductos.models.services;

import java.util.List;

import com.svillanueva.springboot.app.commons.models.entity.Producto;

public interface ProductoService {
  List<Producto> findAll();

  Producto findById(Long id);

  Producto save(Producto producto);

  void deleteById(Long id);
}
