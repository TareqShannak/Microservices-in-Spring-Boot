package com.example.storage.service;

import com.example.storage.model.FirstItem;
import com.example.storage.repo.FirstItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstItemService {

    @Autowired
    private FirstItemRepository firstItemRepository;

    public void saveItem(FirstItem firstItem){
        firstItemRepository.save(firstItem);
    }
}
