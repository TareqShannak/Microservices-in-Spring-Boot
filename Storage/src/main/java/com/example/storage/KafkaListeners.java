package com.example.storage;

import com.example.common.model.Attribute;
import com.example.common.model.KafkaEvent;
import com.example.storage.service.ObjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class KafkaListeners {

    @Autowired
    private ObjectService objectService;

    KafkaEvent kafkaEvent;

    @KafkaListener(topics = "json", groupId = "uniqueGroup", containerFactory = "itemListener")
    void listener(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        kafkaEvent = mapper.readValue(data, KafkaEvent.class);
        System.out.println("JSON_CONVERTER ==> STORAGE: " + kafkaEvent);

        List<Attribute> attributesWithNamedKeys = new ArrayList<>() {
            {
                List<String> itemAttributes = objectService.getItemAttributes(kafkaEvent.getFormId());
                int count = 0;
                for (String itemAttribute : itemAttributes) {
                    if (itemAttribute.equals("id")) continue;
                    add(new Attribute(itemAttribute, kafkaEvent.getAttributes().get(count++).getValue()));
                }
            }
        };

        kafkaEvent.setAttributes(attributesWithNamedKeys);

        String columns = "";
        String values = "";
        for (Attribute attribute : kafkaEvent.getAttributes()) {
            columns = columns.concat(attribute.getName() + ", ");
            values = values.concat(attribute.getName().equals("id") ? attribute.getValue() + ", " : "'" + attribute.getValue() + "', ");
        }

        //Reformat the columns and values to insert in the query
        columns = columns.trim().substring(0, columns.trim().length() - 1);
        values = values.trim().substring(0, values.trim().length() - 1);

        objectService.saveItem(kafkaEvent.getFormId(), columns, values);
    }
}
