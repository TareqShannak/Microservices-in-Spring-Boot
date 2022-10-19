package com.example.apis.controller;

import com.example.apis.model.Item;
import com.example.apis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.allItems();
    }

    @GetMapping("/id/{id}")
    public Item getItemById(@PathVariable("id") Long id) {
        return itemService.getById(id);
    }

    @GetMapping("/capacity/{capacity}")
    public List<Item> getItemsByCapacity(@PathVariable("capacity") int capacity) {
        return itemService.getByCapacity(capacity);
    }

    @GetMapping("/capacity_greater_than_equal/{capacity}")
    public List<Item> getItemsByCapacityGreaterThanEqual(@PathVariable("capacity") int capacity) {
        return itemService.getByCapacityGreaterThanEqual(capacity);
    }

    @GetMapping("/export_to_contains/{export_to}")
    public List<Item> getItemsByExportToContains(@PathVariable("export_to") String exportTo) {
        return itemService.getByExportToContains(exportTo);
    }

    @GetMapping("/name_contains/{name}")
    public List<Item> getItemsByNameContains(@PathVariable("name") String name) {
        return itemService.getByNameContains(name);
    }

    @GetMapping("/percentage_between")
    public List<Item> getItemsByPercentageBetween(@RequestParam("min") double min, @RequestParam("max") double max) {
        return itemService.getByPercentageBetween(min, max);
    }


}
