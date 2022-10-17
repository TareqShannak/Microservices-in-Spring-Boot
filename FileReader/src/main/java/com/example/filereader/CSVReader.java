package com.example.filereader;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

@Component
public class CSVReader {
    public static final String delimiter = ",";

    public static ArrayList<String> read(String csvFile) {
        ArrayList<String> result = new ArrayList<>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                result.add(line);

//                tempArr = line.split(delimiter);
//                for (String tempStr : tempArr) {
//                    System.out.print(tempStr + " ");
//                }
//                System.out.println();
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
