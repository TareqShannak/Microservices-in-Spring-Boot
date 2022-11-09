package com.example.storage.repo;


import com.example.storage.model.IntegrationMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationMappingRepository extends MongoRepository<IntegrationMapping, String> {

}
