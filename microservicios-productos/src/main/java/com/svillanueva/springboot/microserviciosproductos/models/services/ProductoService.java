package com.svillanueva.springboot.microserviciosproductos.models.services;

import java.util.List;

import com.svillanueva.springboot.microserviciosproductos.models.entity.Producto;

public interface ProductoService {
  List<Producto> findAll();

  Producto findById(Long id);
}
