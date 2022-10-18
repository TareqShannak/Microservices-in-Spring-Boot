package com.example.filereader.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic tareqTopic() {
        return TopicBuilder.name("logs").build();
    }

    @Bean
    public NewTopic dataTopic() {
        return TopicBuilder.name("import-data").build();
    }
}
