package com.example.storage.service;

import com.example.storage.model.IntegrationMapping;
import com.example.storage.model.Monitor;
import com.example.storage.repo.IntegrationMappingRepository;
import com.example.storage.repo.MonitorRepository;
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