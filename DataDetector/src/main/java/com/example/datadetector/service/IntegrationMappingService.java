package com.example.datadetector.service;

import com.example.datadetector.model.IntegrationMapping;
import com.example.datadetector.repo.IntegrationMappingRepository;
import org.springframework.stereotype.Service;

@Service
public class IntegrationMappingService {

    private final IntegrationMappingRepository integrationMappingRepository;

    public IntegrationMappingService(IntegrationMappingRepository integrationMappingRepository) {
        this.integrationMappingRepository = integrationMappingRepository;
    }

    public IntegrationMapping getIntegrationMappingById(String integrationMappingId){
        return integrationMappingRepository.findById(integrationMappingId).get();
    }
}