package com.example.jsonconverter;

import com.example.common.model.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaListeners {

    @Autowired
    KafkaTemplate<String, JsonNode> kafkaTemplate;
    JsonNode jsonNode;

//    JSONObject jo = new JSONObject();
    String[] attributes;

    @KafkaListener(topics = "logs", groupId = "uniqueGroup")
    void listener(String data) throws JSONException {
        System.out.println("Data received From FILE_READER: " + data);
        convert(data);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(jsonNode);
            System.out.println("Data converted to JSON and Send From JSON_CONVERTER: " + json);
            kafkaTemplate.send("json", jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    void convert(String data) throws JSONException {
        attributes = data.split(",");
        jsonNode = new JsonNode(Long.parseLong(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]),
                Double.parseDouble(attributes[4]), Double.parseDouble(attributes[5]), Double.parseDouble(attributes[6]),
                attributes[7], attributes[8], Double.parseDouble(attributes[9]));

//        jo.put("id", Long.parseLong(attributes[0]));
//        jo.put("name", attributes[1]);
//        jo.put("owner", attributes[2]);
//        jo.put("capacity", Integer.parseInt(attributes[3]));
//        jo.put("amount", Double.parseDouble(attributes[4]));
//        jo.put("total", Double.parseDouble(attributes[5]));
//        jo.put("profit", Double.parseDouble(attributes[6]));
//        jo.put("reference", attributes[7]);
//        jo.put("exportTo", attributes[8]);
//        jo.put("percentage", Double.parseDouble(attributes[9]));

    }
}

