package com.example.jsonconverter;

import com.example.common.model.Attribute;
import com.example.common.model.DataLine;
import com.example.common.model.KafkaEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class KafkaListeners {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
//    JSONObject object;

    KafkaEvent kafkaEvent;
    String[] tokens;

    @KafkaListener(topics = "logs", groupId = "uniqueGroup")
    void listener(String data) throws JSONException, JsonProcessingException {

//        List<Attribute> attributes = new ArrayList<>() {
//            {
//                List<String> itemAttributes = objectService.getItemAttributes(formId);
//                for (String itemAttribute : itemAttributes) {
//                    if (itemAttribute.equals("id"))
//                        continue;
//                    add(new Attribute(itemAttribute, object.getString(String.valueOf(itemAttributes.indexOf(itemAttribute)))));
//                }
//            }
//        };
//        KafkaEvent event = new KafkaEvent(formId, attributes);
//
//
        System.out.println("FILE_READER ==> JSON_CONVERTER: " + data);
        ObjectMapper mapper = new ObjectMapper();
        DataLine dataLine = mapper.readValue(data, DataLine.class);
        convertToKafkaEvent(dataLine);

        String json = kafkaEvent.toString();
        System.out.println("JSON_CONVERTER ==> STORAGE: " + json);
        kafkaTemplate.send("json", json);

    }

    void convertToKafkaEvent(DataLine dataLine) {


        tokens = dataLine.getData().split(",");

        List<Attribute> attributes = new ArrayList<>();

        int count = 0;
        for (String token : tokens) {
            attributes.add(new Attribute(String.valueOf(count++), token));
        }


//        Map<String, String> map = new HashMap<>();
//        int count = 0;
//        for (String attribute : attributes) {
//            map.put(String.valueOf(count++), attribute);
//        }

        kafkaEvent = new KafkaEvent(dataLine.getFormId(), attributes);
//        object = new JSONObject(map);

    }
}

