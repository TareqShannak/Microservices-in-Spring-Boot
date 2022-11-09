package com.example.storage.service;

import com.example.common.model.Attribute;
import com.example.storage.repo.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

//    public void saveItem(int formId, String columns, String values) {
//        objectRepository.insertWithQuery(Tables.values()[formId - 1].toString(), columns, values);
//    }

    public void createTable(String tableName, List<Attribute> attributes){

//        String columns = "id int8 not null, ";
        String columns = "id SERIAL PRIMARY KEY, ";
        for (Attribute attribute : attributes) {
            columns = columns.concat(attribute.getName() + " varchar(255), ");
        }

        //Reformat the columns and values to insert in the query
        columns = columns.trim().substring(0, columns.trim().length() - 1);

        objectRepository.createQuery(tableName, columns);
    }
    public void saveItem(String tableName, List<Attribute> attributes) {
        String columns = "";
        String values = "";
        for (Attribute attribute : attributes) {
            columns = columns.concat(attribute.getName() + ", ");
            values = values.concat("'" + attribute.getValue() + "', ");
        }

        //Reformat the columns and values to insert in the query
        columns = columns.trim().substring(0, columns.trim().length() - 1);
        values = values.trim().substring(0, values.trim().length() - 1);

        objectRepository.insertWithQuery(tableName, columns, values);
    }
}
