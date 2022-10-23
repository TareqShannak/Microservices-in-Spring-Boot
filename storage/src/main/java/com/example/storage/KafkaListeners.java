package com.example.storage;

import com.example.storage.model.FirstItem;
import com.example.storage.model.FirstItemAttributes;
import com.example.storage.model.SecondItem;
import com.example.storage.model.SecondItemAttributes;
import com.example.storage.service.SecondItemService;
import com.example.storage.service.FirstItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class KafkaListeners {

    @Autowired
    private FirstItemService firstItemService;

    @Autowired
    private SecondItemService secondItemService;

    JSONObject object;

    @KafkaListener(topics = "json", groupId = "uniqueGroup", containerFactory = "itemListener")
    void listener(String data) {
        try {
            object = new JSONObject(data);
            System.out.println("Data received From JSON_CONVERTER: " + object);
            int formId = object.getInt("0");
            object.remove("0");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> properties;

            if (formId == 1) {
                properties = new HashMap<>() {{
                    for (FirstItemAttributes value : FirstItemAttributes.values()) {
                        put(value.toString(), object.getString(String.valueOf(value.ordinal() + 1)));
                    }
                }};

                object = new JSONObject(properties);
                FirstItem firstItem = mapper.readValue(object.toString(), FirstItem.class);
                System.out.println("Data received Item1 From JSON_CONVERTER: " + firstItem);
                firstItemService.saveItem(firstItem);
            } else {
                properties = new HashMap<>() {{
                    for (SecondItemAttributes value : SecondItemAttributes.values()) {
                        put(value.toString(), object.getString(String.valueOf(value.ordinal() + 1)));
                    }
                }};

                object = new JSONObject(properties);
                SecondItem secondItem = mapper.readValue(object.toString(), SecondItem.class);
                System.out.println("Data received Item2 From JSON_CONVERTER: " + secondItem);
                secondItemService.saveItem(secondItem);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
