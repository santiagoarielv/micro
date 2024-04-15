package com.svillanueva.springboot.microserviciosproductos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.svillanueva.springboot.app.commons.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
