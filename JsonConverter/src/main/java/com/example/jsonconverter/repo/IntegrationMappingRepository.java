package com.example.jsonconverter.repo;

import com.example.jsonconverter.model.IntegrationMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationMappingRepository extends MongoRepository<IntegrationMapping, String> {
}
