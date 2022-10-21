package com.example.storage.service;

import com.example.storage.model.SecondItem;
import com.example.storage.repo.SecondItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondItemService {

    @Autowired
    private SecondItemRepository secondItemRepository;

    public void saveItem(SecondItem secondItem){
        secondItemRepository.save(secondItem);
    }
}
