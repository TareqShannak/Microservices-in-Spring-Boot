package com.example.jsonconverter.service;

import com.example.jsonconverter.model.IntegrationMapping;
import com.example.jsonconverter.model.Monitor;
import com.example.jsonconverter.repo.IntegrationMappingRepository;
import com.example.jsonconverter.repo.MonitorRepository;
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
