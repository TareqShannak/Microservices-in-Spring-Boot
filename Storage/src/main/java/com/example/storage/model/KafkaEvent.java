package com.example.storage.model;

import java.time.LocalDateTime;
import java.util.List;

public class KafkaEvent {

    private int formId;

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
        this.creationTime = creationTime;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "KafkaEvent{" +
                "formId=" + formId +
                ", creationTime=" + creationTime +
                ", attributes=" + attributes +
                '}';
    }
}
