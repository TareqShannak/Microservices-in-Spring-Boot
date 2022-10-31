package com.example.datadetector.model;

import com.example.common.model.DataFile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Monitor implements Runnable {

    private int id;

    private String[] namingPolicy;

    private String folderPath;

    private double checkTimeInMinutes;

    private Timestamp startTime;

    private KafkaTemplate<String, String> kafkaTemplate;

    public Monitor() {
    }

    public Monitor(int id, String[] namingPolicy, String folderPath, double checkTimeInMinutes, Timestamp startTime) {
        this.id = id;
        this.namingPolicy = namingPolicy;
        this.folderPath = folderPath;
        this.checkTimeInMinutes = checkTimeInMinutes;
        this.startTime = startTime;
    }

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }

    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getNamingPolicy() {
        return namingPolicy;
    }

    public void setNamingPolicy(String[] namingPolicy) {
        this.namingPolicy = namingPolicy;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public double getCheckTimeInMinutes() {
        return checkTimeInMinutes;
    }

    public void setCheckTimeInMinutes(double checkTimeInMinutes) {
        this.checkTimeInMinutes = checkTimeInMinutes;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Monitor{" + "id=" + id + ", namingPolicy=" + Arrays.toString(namingPolicy) + ", folderPath='" + folderPath + '\'' + ", checkTimeInMinutes=" + checkTimeInMinutes + ", startTime=" + startTime + '}';
    }


    @Override
    public void run() {
        try {
            System.out.println("New Monitor!");
            while (true) {
                for (File newFile : listLastUpdatedFiles(new File(this.getFolderPath()), this.getCheckTimeInMinutes())) {
                    for (String s : this.getNamingPolicy())
                        if (newFile.getName().matches(s)) {
                            System.out.println("New File!");
                            kafkaTemplate.send("import-data", new DataFile(this.getFolderPath().replaceAll("\\\\", "\\\\\\\\") + "\\\\" + newFile.getName(), this.getId()).toString());
                            break;
                        }
                }
                Thread.sleep((long) (this.getCheckTimeInMinutes() * 60_000));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<File> listLastUpdatedFiles(File folder, double sleepDurationInMinutes) throws Exception {
        List<File> newFileList = new ArrayList<File>();
        if (folder.listFiles() == null) return newFileList;
        for (File fileEntry : folder.listFiles()) {
            double sleepDurationInMillis = sleepDurationInMinutes * 60 * 1000;
            if ((System.currentTimeMillis() - fileEntry.lastModified()) <= sleepDurationInMillis || (System.currentTimeMillis() - Files.readAttributes(Paths.get(fileEntry.toURI()), BasicFileAttributes.class).creationTime().toMillis()) <= sleepDurationInMillis || (System.currentTimeMillis() - Files.readAttributes(Paths.get(fileEntry.toURI()), BasicFileAttributes.class).lastAccessTime().toMillis()) <= sleepDurationInMillis)
                newFileList.add(fileEntry);
        }
        return newFileList;
    }
}
