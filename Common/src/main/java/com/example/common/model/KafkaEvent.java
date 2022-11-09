package com.example.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class KafkaEvent {

    private String mappingId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime creationTime;

    private List<Attribute> attributes;

    public KafkaEvent() {
    }

    public KafkaEvent(String mappingId, List<Attribute> attributes) {
        this.mappingId = mappingId;
        this.creationTime = LocalDateTime.now();
        this.attributes = attributes;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
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
                "\"mappingId\":\"" + mappingId +
                "\", \"creationTime\":\"" + creationTime +
                "\", \"attributes\":" + attributes +
                '}';
    }
}
