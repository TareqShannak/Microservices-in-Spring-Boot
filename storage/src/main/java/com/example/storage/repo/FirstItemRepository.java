package com.example.storage.repo;

import com.example.storage.model.FirstItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstItemRepository extends JpaRepository<FirstItem, Long> {
}
