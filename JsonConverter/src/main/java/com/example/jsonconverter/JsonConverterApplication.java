package com.example.jsonconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonConverterApplication.class, args);
    }

    @Autowired
    KafkaListeners kafkaListeners;
}
