package com.example.storage.repo;

import com.example.storage.model.SecondItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondItemRepository extends JpaRepository<SecondItem, Long> {
}
