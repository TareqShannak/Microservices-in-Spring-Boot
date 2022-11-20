package com.example.datadetector;

import com.example.datadetector.repo.IntegrationMappingRepository;
import com.example.datadetector.repo.MonitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@SpringBootApplication
@Controller
public class DataDetectorApplication {
    private final ConfigurationReader configurationReader;

    private final MonitorRepository monitorRepository;

    private final IntegrationMappingRepository integrationMappingRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;


    public DataDetectorApplication(ConfigurationReader configurationReader, MonitorRepository monitorRepository, IntegrationMappingRepository integrationMappingRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.configurationReader = configurationReader;
        this.monitorRepository = monitorRepository;
        this.integrationMappingRepository = integrationMappingRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataDetectorApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            integrationMappingRepository.deleteAll();
            monitorRepository.deleteAll();

            String filePath = "C:\\Users\\TareqS\\Desktop\\Config.txt";
            ArrayList<String> events = ConfigurationReader.read(filePath);
            System.out.println("Data Read From Configuration File: " + events);

            for (String event : events){
                Thread.sleep(1_000);
                kafkaTemplate.send("monitor-data", event);
            }
        };
    }
}
