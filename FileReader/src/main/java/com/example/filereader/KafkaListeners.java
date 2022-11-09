package com.example.filereader;

import com.example.common.model.DataFile;
import com.example.common.model.DataLine;
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
            System.out.println("DATA_DETECTOR ==> FILE_READER: " + data);
            dataFile = mapper.readValue(data, DataFile.class);

            String csvFile = dataFile.getPath();
            ArrayList<String> dataLines = fileReader.read(csvFile);

            for (String line : dataLines) {
                DataLine dataLine = new DataLine(dataFile.getMonitorId(), line);
                System.out.println("FILE_READER ==> JSON_CONVERTER: " + dataLine);
                kafkaTemplate.send("logs", dataLine.toString());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}