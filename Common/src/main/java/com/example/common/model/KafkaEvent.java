package com.example.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class KafkaEvent {

    private int formId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creationTime;

    private List<Attribute> attributes;

    public KafkaEvent() {
    }

    public KafkaEvent(int formId, List<Attribute> attributes) {
        this.formId = formId;
        this.creationTime = LocalDateTime.now();
        this.attributes = attributes;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = LocalDateTime.now();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "{" +
                "\"formId\":" + formId +
                ", \"creationTime\":\"" + creationTime +
                "\", \"attributes\":" + attributes +
                '}';
    }
}
