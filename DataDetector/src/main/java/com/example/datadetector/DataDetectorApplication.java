package com.example.datadetector;

import com.example.datadetector.model.ActiveMonitors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataDetectorApplication {
    @Autowired
    private ActiveMonitors activeMonitors;

    public static void main(String[] args) {
        SpringApplication.run(DataDetectorApplication.class, args);
    }
}
