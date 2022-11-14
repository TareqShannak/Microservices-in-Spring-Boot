package com.example.storage;

import com.example.common.model.KafkaEvent;
import com.example.storage.model.IntegrationMapping;
import com.example.storage.service.IntegrationMappingService;
import com.example.storage.service.ObjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaListeners {

    @Autowired
    private IntegrationMappingService integrationMappingService;

    @Autowired
    private ObjectService objectService;

    KafkaEvent kafkaEvent;

    @KafkaListener(topics = "json", groupId = "uniqueGroup")
    void listener(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        kafkaEvent = mapper.readValue(data, KafkaEvent.class);
        System.out.println("JSON_CONVERTER ==> STORAGE: " + kafkaEvent);

        IntegrationMapping currentIntegrationMapping = integrationMappingService.getIntegrationMappingById(kafkaEvent.getMappingId());

        objectService.createTable(currentIntegrationMapping.getTableName(), kafkaEvent.getAttributes());
        objectService.saveItem(currentIntegrationMapping.getTableName(), kafkaEvent.getAttributes());
    }
}
