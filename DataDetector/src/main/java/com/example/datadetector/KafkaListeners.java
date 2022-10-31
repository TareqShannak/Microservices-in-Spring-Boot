package com.example.datadetector;

import com.example.datadetector.model.ActiveMonitors;
import com.example.datadetector.model.Monitor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Component
public class KafkaListeners {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ActiveMonitors activeMonitors;

    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "monitor-data", groupId = "uniqueGroup")
    void listener(String data) {
        try {
            Monitor monitor = mapper.readValue(data, Monitor.class);
            monitor.setKafkaTemplate(kafkaTemplate);
            Path path = Paths.get(monitor.getFolderPath());
            if (!Files.exists(path)) Files.createDirectory(path);

            if (activeMonitors.getMonitorList() == null) activeMonitors.setMonitorList(new ArrayList<>());
            activeMonitors.getMonitorList().add(monitor);
            Thread th = new Thread(monitor);
            th.start();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}