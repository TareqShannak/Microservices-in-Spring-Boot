package com.example.datadetector.repo;

import com.example.datadetector.model.IntegrationMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationMappingRepository extends MongoRepository<IntegrationMapping, String> {
}
