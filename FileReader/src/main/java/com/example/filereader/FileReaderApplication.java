package com.example.filereader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.*;
import java.util.ArrayList;

@SpringBootApplication
public class FileReaderApplication {

    @Autowired
    private CSVReader csvReader;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(FileReaderApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String csvFile = "C:\\Users\\TareqS\\Desktop\\Sample1.csv";
            ArrayList<String> events = csvReader.read(csvFile);

            for (String event : events) {
                System.out.println("Data Send From FILE_READER: " + event);
                kafkaTemplate.send("logs", event);
            }


        };
    }


}
