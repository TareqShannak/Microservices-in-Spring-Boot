package com.example.storage;

import com.example.storage.model.*;
import com.example.storage.service.ObjectService;
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

    JSONObject object;

    @KafkaListener(topics = "json", groupId = "uniqueGroup", containerFactory = "itemListener")
    void listener(String data) {
        try {
            object = new JSONObject(data);
            System.out.println("Data received From JSON_CONVERTER: " + object);

            int formId = object.getInt("0");

            List<Attribute> attributes = new ArrayList<>() {
                {
                    List<String> itemAttributes = objectService.getItemAttributes(formId);
                    for (String itemAttribute : itemAttributes) {
                        if (itemAttribute.equals("id"))
                            continue;
                        add(new Attribute(itemAttribute, object.getString(String.valueOf(itemAttributes.indexOf(itemAttribute)))));
                    }

//                    for (FirstItemAttributes value : FirstItemAttributes.values())
//                        add(new Attribute(value.toString(), object.getString(String.valueOf(value.ordinal() + 1))));
                }
            };
            KafkaEvent event = new KafkaEvent(formId, attributes);

            String columns = "";
            String values = "";
            for (Attribute attribute : event.getAttributes()) {
                columns = columns.concat(attribute.getName() + ", ");
                values = values.concat(attribute.getName().equals("id") ? attribute.getValue() + ", " : "'" + attribute.getValue() + "', ");
            }

            //Reformat the columns and values to insert in the query
            columns = columns.trim().substring(0, columns.trim().length() - 1);
            values = values.trim().substring(0, values.trim().length() - 1);

            objectService.saveItem(event.getFormId(), columns, values);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
