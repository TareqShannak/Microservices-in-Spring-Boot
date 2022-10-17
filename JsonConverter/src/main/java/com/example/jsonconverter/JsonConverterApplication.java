package com.example.jsonconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JsonConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonConverterApplication.class, args);
    }

    @Autowired
    KafkaListeners kafkaListeners;
    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {


        };
    }
}
