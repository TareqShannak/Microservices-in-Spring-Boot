package com.example.jsonconverter.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveMonitors {
    private List<Monitor> monitorList;

    public ActiveMonitors() {
    }

    public ActiveMonitors(List<Monitor> monitorList) {
        this.monitorList = monitorList;
    }

    public List<Monitor> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<Monitor> monitorList) {
        this.monitorList = monitorList;
    }
}
