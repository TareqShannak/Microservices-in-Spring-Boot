package com.example.apis.repo;

import com.example.apis.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    public List<Item> findByOrderById();

    public List<Item> findByCapacity(int capacity);

    public List<Item> findByCapacityGreaterThanEqual(int capacity);

    public List<Item> findByExportToContainsIgnoreCase(String exportTo);

    public List<Item> findByNameContainsIgnoreCase(String name);

    public List<Item> findByPercentageBetweenOrderByPercentage(double min, double max);
}
