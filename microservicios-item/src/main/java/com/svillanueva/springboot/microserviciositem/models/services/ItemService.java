package com.svillanueva.springboot.microserviciositem.models.services;

import java.util.List;

import com.svillanueva.springboot.microserviciositem.models.Item;

public interface ItemService {

  List<Item> findAll();

  Item findById(Long id, Integer cantidad);
}
