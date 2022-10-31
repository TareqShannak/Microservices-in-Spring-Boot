package com.example.filereader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

@Component
public class FileReader {
    public static ArrayList<String> read(String path) {
        ArrayList<String> result = new ArrayList<>();
        try {
            File file = new File(path);
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) result.add(line);
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
