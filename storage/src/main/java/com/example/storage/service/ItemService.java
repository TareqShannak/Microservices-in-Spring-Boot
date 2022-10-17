package com.example.storage.service;

import com.example.storage.model.Item;
import com.example.storage.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
