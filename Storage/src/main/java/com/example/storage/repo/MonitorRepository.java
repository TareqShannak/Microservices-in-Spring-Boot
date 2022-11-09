package com.example.storage.repo;


import com.example.storage.model.Monitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends MongoRepository<Monitor, String> {

}
