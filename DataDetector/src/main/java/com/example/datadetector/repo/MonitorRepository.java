package com.example.datadetector.repo;

import com.example.datadetector.model.Monitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends MongoRepository<Monitor, String> {

    public Monitor findByFormId(int formId);
}
