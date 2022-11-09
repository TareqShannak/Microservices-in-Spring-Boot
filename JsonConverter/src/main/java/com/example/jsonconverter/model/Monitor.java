package com.example.jsonconverter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Document
public class Monitor {

    @Id
    private String id;

    private int formId;

    private String[] namingPolicy;

    private String folderPath;

    private double checkTimeInMinutes;

    private LocalDateTime startTime;

    private List<IntegrationMapping> integrationMappings;

    public Monitor() {
    }

    public Monitor(int formId, String[] namingPolicy, String folderPath, double checkTimeInMinutes, LocalDateTime startTime, List<IntegrationMapping> integrationMappings) {
        this.formId = formId;
        this.namingPolicy = namingPolicy;
        this.folderPath = folderPath;
        this.checkTimeInMinutes = checkTimeInMinutes;
        this.startTime = startTime;
        this.integrationMappings = integrationMappings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String[] getNamingPolicy() {
        return namingPolicy;
    }

    public void setNamingPolicy(String[] namingPolicy) {
        this.namingPolicy = namingPolicy;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public double getCheckTimeInMinutes() {
        return checkTimeInMinutes;
    }

    public void setCheckTimeInMinutes(double checkTimeInMinutes) {
        this.checkTimeInMinutes = checkTimeInMinutes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = LocalDateTime.now();
    }

    public List<IntegrationMapping> getIntegrationMappings() {
        return integrationMappings;
    }

    public void setIntegrationMappings(List<IntegrationMapping> integrationMappings) {
        this.integrationMappings = integrationMappings;
    }

    @Override
    public String toString() {
        return "Monitor{" + "formId=" + formId + ", namingPolicy=" + Arrays.toString(namingPolicy) + ", folderPath='" + folderPath + '\'' + ", checkTimeInMinutes=" + checkTimeInMinutes + ", startTime=" + startTime + '}';
    }

}
