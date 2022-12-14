package com.example.datadetector.service;

import com.example.datadetector.model.IntegrationMapping;
import com.example.datadetector.model.Monitor;
import com.example.datadetector.repo.IntegrationMappingRepository;
import com.example.datadetector.repo.MonitorRepository;
import org.springframework.stereotype.Service;
@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    private final IntegrationMappingRepository integrationMappingRepository;

    public MonitorService(MonitorRepository monitorRepository, IntegrationMappingRepository integrationMappingRepository) {
        this.monitorRepository = monitorRepository;
        this.integrationMappingRepository = integrationMappingRepository;
    }

    public void saveMonitor(Monitor newMonitor){
        for (IntegrationMapping integrationMapping : newMonitor.getIntegrationMappings()) {
            integrationMappingRepository.insert(integrationMapping);
        }
        monitorRepository.insert(newMonitor);
    }
    public Monitor getMonitorById(String monitorID){
        return monitorRepository.findById(monitorID).get();
    }
}