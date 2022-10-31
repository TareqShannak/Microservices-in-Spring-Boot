package com.example.storage.service;

import com.example.storage.model.Tables;
import com.example.storage.repo.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    public void saveItem(int formId, String columns, String values) {
        objectRepository.insertWithQuery(Tables.values()[formId - 1].toString(), columns, values);
    }

    public List<String> getItemAttributes(int formId) {
        return objectRepository.getAttributes(Tables.values()[formId - 1].toString());
    }
}
