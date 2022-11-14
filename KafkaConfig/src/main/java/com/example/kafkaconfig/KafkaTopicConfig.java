package com.example.datadetector.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic monitorDataTopic() {
        return TopicBuilder.name("monitor-data").build();
    }

    @Bean
    public NewTopic importDataTopic() {
        return TopicBuilder.name("import-data").build();
    }

    @Bean
    public NewTopic tareqTopic() {
        return TopicBuilder.name("logs").build();
    }

    @Bean
    public NewTopic jsonTopic() {
        return TopicBuilder.name("json").build();
    }
}
