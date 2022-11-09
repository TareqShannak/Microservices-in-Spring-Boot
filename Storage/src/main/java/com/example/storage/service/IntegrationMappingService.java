package com.example.storage.service;

import com.example.storage.model.IntegrationMapping;
import com.example.storage.repo.IntegrationMappingRepository;
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
