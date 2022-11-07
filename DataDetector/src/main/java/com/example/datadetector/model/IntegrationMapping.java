package com.example.datadetector.model;

import com.example.common.model.Attribute;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class IntegrationMapping {

    @Id
    private String id;

    private int mappingId;

    private String tableName;

    private List<Attribute> translationMapping;

    public IntegrationMapping() {
    }

    public IntegrationMapping(int mappingId, String tableName, List<Attribute> translationMapping) {
        this.mappingId = mappingId;
        this.tableName = tableName;
        this.translationMapping = translationMapping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMappingId() {
        return mappingId;
    }

    public void setMappingId(int mappingId) {
        this.mappingId = mappingId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Attribute> getTranslationMapping() {
        return translationMapping;
    }

    public void setTranslationMapping(List<Attribute> translationMapping) {
        this.translationMapping = translationMapping;
    }

    @Override
    public String toString() {
        return "IntegrationMapping{" +
                "mappingId=" + mappingId +
                ", tableName='" + tableName + '\'' +
                ", translationMapping=" + translationMapping +
                '}';
    }
}
