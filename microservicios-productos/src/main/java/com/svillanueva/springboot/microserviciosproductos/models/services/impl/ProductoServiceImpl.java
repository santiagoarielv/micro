package com.svillanueva.springboot.microserviciosproductos.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svillanueva.springboot.microserviciosproductos.models.dao.ProductoRepository;
import com.svillanueva.springboot.app.commons.models.entity.Producto;
import com.svillanueva.springboot.microserviciosproductos.models.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
  @Autowired
  private ProductoRepository productoRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Producto> findAll() {
    return (List<Producto>) productoRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Producto findById(Long id) {
    return productoRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {

    productoRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Producto save(Producto producto) {
    return productoRepository.save(producto);
  }

}
