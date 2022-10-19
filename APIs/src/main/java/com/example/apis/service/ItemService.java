package com.example.apis.service;

import com.example.apis.model.Item;
import com.example.apis.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> allItems(){
        return itemRepository.findByOrderById();
    }

    public Item getById(Long id){
        return itemRepository.findById(id).get();
    }

    public List<Item> getByCapacity(int capacity){
        return itemRepository.findByCapacity(capacity);
    }
    public List<Item> getByCapacityGreaterThanEqual(int capacity){
        return itemRepository.findByCapacityGreaterThanEqual(capacity);
    }

    public List<Item> getByExportToContains(String exportTo){
        return itemRepository.findByExportToContainsIgnoreCase(exportTo);
    }

    public List<Item> getByNameContains(String name){
        return itemRepository.findByNameContainsIgnoreCase(name);
    }

    public List<Item> getByPercentageBetween(double min, double max){
        return itemRepository.findByPercentageBetweenOrderByPercentage(min, max);
    }

}
