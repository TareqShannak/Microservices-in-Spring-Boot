package com.example.datadetector;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class ConfigurationReader {

    public static ArrayList<String> read(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

}
