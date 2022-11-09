package com.example.common.model;

public class DataLine {

    private String monitorId;

    private String data;

    public DataLine() {
    }

    public DataLine(String monitorId, String data) {
        this.monitorId = monitorId;
        this.data = data;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"monitorId\":\"" + monitorId +
                "\", \"data\":\"" + data + '\"' +
                '}';
    }
}