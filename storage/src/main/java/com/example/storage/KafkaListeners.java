package com.example.storage;

import com.example.storage.model.Item;
import com.example.common.model.JsonNode;
import com.example.storage.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaListeners {

    @Autowired
    private ItemService itemService;

    @KafkaListener(topics = "json", groupId = "uniqueGroup", containerFactory = "itemListener")
    void listener(String data) {
        ObjectMapper mapper = new ObjectMapper();
        Item received = null;
        try {
            received = mapper.readValue(data, Item.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data received From JSON_CONVERTER: " + received);
        itemService.saveItem(received);
    }
}
