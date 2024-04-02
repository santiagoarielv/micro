package com.svillanueva.springboot.microserviciosproductos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.svillanueva.springboot.microserviciosproductos.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
