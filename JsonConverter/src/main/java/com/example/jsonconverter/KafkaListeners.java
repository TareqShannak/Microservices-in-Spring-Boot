package com.example.jsonconverter;

import com.example.common.model.Attribute;
import com.example.common.model.DataLine;
import com.example.common.model.KafkaEvent;
import com.example.jsonconverter.model.IntegrationMapping;
import com.example.jsonconverter.model.Monitor;
import com.example.jsonconverter.service.MonitorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class KafkaListeners {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    KafkaEvent kafkaEvent;
    String[] tokens;

    @KafkaListener(topics = "logs", groupId = "uniqueGroup")
    void listener(String data) throws JsonProcessingException {

        System.out.println("FILE_READER ==> JSON_CONVERTER: " + data);
        ObjectMapper mapper = new ObjectMapper();
        DataLine dataLine = mapper.readValue(data, DataLine.class);
        convertToKafkaEvent(dataLine);
    }

    void convertToKafkaEvent(DataLine dataLine) {

        List<Attribute> attributes = new ArrayList<>();

        Monitor currentMonitor = monitorService.getMonitorById(dataLine.getMonitorId());
        tokens = dataLine.getData().split(currentMonitor.getDataDelimiter());

        for (IntegrationMapping integrationMapping : currentMonitor.getIntegrationMappings()) {
            if(getOccurrencesOfChar(dataLine.getData(), currentMonitor.getDataDelimiter()) + 1 != integrationMapping.getTranslationMapping().size()){
                System.out.println("Wrong Format for Data");
                continue;
            }
            AtomicInteger count = new AtomicInteger();
            for (String token : tokens)
                attributes.add(new Attribute(findUsingIndex(String.valueOf(count.getAndIncrement()),
                        integrationMapping.getTranslationMapping()).getName(), token));
            kafkaEvent = new KafkaEvent(integrationMapping.getId(), attributes);
            String json = kafkaEvent.toString();
            System.out.println("JSON_CONVERTER ==> STORAGE: " + json);
            kafkaTemplate.send("json", json);
        }
    }

    public Attribute findUsingIndex(String index, List<Attribute> attributes) {

        for (Attribute attribute : attributes) {
            if (attribute.getValue().equals(index)) {
                return attribute;
            }
        }
        return null;
    }

    public int getOccurrencesOfChar(String sentence, String characters){
        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            if (characters.contains(String.valueOf(sentence.charAt(i)))) {
                count++;
            }
        }

        return count;
    }
}

