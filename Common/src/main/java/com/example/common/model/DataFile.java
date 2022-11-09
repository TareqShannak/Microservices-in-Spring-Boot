package com.example.common.model;

public class DataFile {

    private String path;

    private String monitorId;

    public DataFile() {
    }

    public DataFile(String path, String monitorId) {
        this.path = path;
        this.monitorId = monitorId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"path\":\"" + path + '\"' +
                ", \"monitorId\":\"" + monitorId +
                "\"}";
    }
}
