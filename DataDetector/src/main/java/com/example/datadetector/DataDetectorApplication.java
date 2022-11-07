package com.example.datadetector;

import com.example.datadetector.model.ActiveMonitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@SpringBootApplication
@Controller
public class DataDetectorApplication {
    private final ActiveMonitors activeMonitors;

    private final ConfigurationReader configurationReader;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public DataDetectorApplication(ActiveMonitors activeMonitors, ConfigurationReader configurationReader, KafkaTemplate<String, String> kafkaTemplate) {
        this.activeMonitors = activeMonitors;
        this.configurationReader = configurationReader;
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataDetectorApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            String filePath = "C:\\Users\\TareqS\\Desktop\\NewConfig.txt";
            ArrayList<String> events = ConfigurationReader.read(filePath);
            System.out.println("Data Read From Configuration File: " + events);

            for (String event : events){
                Thread.sleep(7_000);
                kafkaTemplate.send("monitor-data", event);
            }
        };
    }
}
