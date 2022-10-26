package com.example.storage.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ObjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(String tableName, String columns, String values) {
        entityManager.createNativeQuery("INSERT INTO " + tableName + "(" + columns + ") VALUES (" + values + ")")
                .executeUpdate();
    }

    @Transactional
    public List<String> getAttributes(String tableName) {
        String s = "SELECT C.column_name FROM information_schema.columns AS C WHERE table_name = '" + tableName + "' ORDER BY cast(NULLIF(regexp_replace(C.dtd_identifier, '\\D', '', 'g'), '') AS integer) ASC";
        return entityManager.createNativeQuery(s)
                .getResultList();
    }
}