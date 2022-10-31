package com.example.jsonconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class KafkaListeners {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    JSONObject object;
    String[] attributes;

    @KafkaListener(topics = "logs", groupId = "uniqueGroup")
    void listener(String data) throws JSONException {
        System.out.println("Data received From FILE_READER: " + data);
        convert(data);
        ObjectMapper mapper = new ObjectMapper();
        String json = object.toString();
        System.out.println("Data converted to JSON and Send From JSON_CONVERTER: " + json);
        kafkaTemplate.send("json", json);

    }

    void convert(String data) throws JSONException {
        attributes = data.split(",");
        Map<String, String> map = new HashMap<>();
        int count = 0;
        for (String attribute : attributes) {
            map.put(String.valueOf(count++), attribute);
        }
        object = new JSONObject(map);

    }
}

