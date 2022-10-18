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
        return itemRepository.findAll();
    }

    public Item getById(Long id){
        return itemRepository.findById(id).get();
    }

    public List<Item> getByCapacity(int capacity){
        return itemRepository.findByCapacity(capacity);
    }

}
