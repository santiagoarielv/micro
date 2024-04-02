package com.svillanueva.springboot.microserviciositem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svillanueva.springboot.microserviciositem.models.Item;
import com.svillanueva.springboot.microserviciositem.services.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @GetMapping
  public List<Item> findAll() {
    return itemService.findAll();
  }

  @GetMapping("/{id}/{cantidad}")
  public Item findById(@PathVariable Long id, @PathVariable Integer cantidad) {
    return itemService.findById(id, cantidad);
  }
}
