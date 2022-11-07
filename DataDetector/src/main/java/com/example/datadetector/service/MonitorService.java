package com.example.datadetector.service;

import com.example.datadetector.model.Monitor;
import com.example.datadetector.repo.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public void saveMonitor(Monitor newMonitor){
        monitorRepository.insert(newMonitor);
    }
}
