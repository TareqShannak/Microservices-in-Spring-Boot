package com.example.datadetector;

import com.example.datadetector.model.ActiveMonitors;
import com.example.datadetector.model.Monitor;

import com.example.datadetector.service.MonitorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class KafkaListeners {

    public static KafkaTemplate<String, String> kafkaTemplate;
    private static ActiveMonitors activeMonitors;

    private static MonitorService monitorService;
    ObjectMapper mapper = new ObjectMapper();

    public KafkaListeners(ActiveMonitors activeMonitors, KafkaTemplate<String, String> kafkaTemplate, MonitorService monitorService) {
        this.kafkaTemplate = kafkaTemplate;
        this.activeMonitors = activeMonitors;
        this.monitorService = monitorService;
    }

    @KafkaListener(topics = "monitor-data", groupId = "uniqueGroup")
    void listener(String data) {
        try {
            Monitor monitor = new Monitor();
            monitor = mapper.readValue(data, Monitor.class);
            monitorService.saveMonitor(monitor);

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