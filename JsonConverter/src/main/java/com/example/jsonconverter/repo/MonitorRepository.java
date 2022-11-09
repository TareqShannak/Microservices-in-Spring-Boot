package com.example.jsonconverter.repo;

import com.example.jsonconverter.model.Monitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends MongoRepository<Monitor, String> {

}
