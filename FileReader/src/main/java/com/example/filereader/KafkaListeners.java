package com.example.filereader;

import com.example.common.model.DataFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class KafkaListeners {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    DataFile dataFile;

    @Autowired
    private FileReader fileReader;

    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "import-data", groupId = "uniqueGroup")
    void listener(String data) {
        try {
            dataFile = mapper.readValue(data, DataFile.class);

            String csvFile = dataFile.getPath();
            ArrayList<String> events = fileReader.read(csvFile);

            for (String event : events) {
                System.out.println("Data Send From FILE_READER: " + event);
                kafkaTemplate.send("logs", dataFile.getFormId() + "," + event);
            }

            //Thread.sleep(30_000);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}