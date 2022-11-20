package com.example.datadetector.model;

import com.example.common.model.DataFile;
import com.example.datadetector.KafkaListeners;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document
public class Monitor implements Runnable {

    @Id
    private String id;

    private int formId;

    private String[] namingPolicy;

    private String folderPath;

    private double checkTimeInMinutes;

    private String dataDelimiter;

    private LocalDateTime startTime;

    private List<IntegrationMapping> integrationMappings;

    public Monitor() {
        this.startTime = LocalDateTime.now();
    }

    public Monitor(int formId, String[] namingPolicy, String folderPath, double checkTimeInMinutes, String dataDelimiter, List<IntegrationMapping> integrationMappings) {
        this.formId = formId;
        this.namingPolicy = namingPolicy;
        this.folderPath = folderPath;
        this.checkTimeInMinutes = checkTimeInMinutes;
        this.startTime = LocalDateTime.now();
        this.dataDelimiter = dataDelimiter;
        this.integrationMappings = integrationMappings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
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

    public String getDataDelimiter() {
        return dataDelimiter;
    }

    public void setDataDelimiter(String dataDelimiter) {
        this.dataDelimiter = dataDelimiter;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = LocalDateTime.now();
    }

    public List<IntegrationMapping> getIntegrationMappings() {
        return integrationMappings;
    }

    public void setIntegrationMappings(List<IntegrationMapping> integrationMappings) {
        this.integrationMappings = integrationMappings;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id='" + id + '\'' +
                ", formId=" + formId +
                ", namingPolicy=" + Arrays.toString(namingPolicy) +
                ", folderPath='" + folderPath + '\'' +
                ", checkTimeInMinutes=" + checkTimeInMinutes +
                ", dataDelimiter='" + dataDelimiter + '\'' +
                ", startTime=" + startTime +
                ", integrationMappings=" + integrationMappings +
                '}';
    }

    @Override
    public void run() {
        try {
            System.out.println("New Monitor! ID: " + this.id);
            while (true) {
                for (File newFile : listLastUpdatedFiles(new File(this.getFolderPath()), this.getCheckTimeInMinutes())) {
                    for (String s : this.getNamingPolicy())
                        if (newFile.getName().matches(s)) {
                            System.out.println("New File Caught By Monitor with ID: " + this.id);
                            System.out.println("DATA_DETECTOR ==> FILE_READER: " + new DataFile(this.getFolderPath().replaceAll("\\\\", "\\\\\\\\") + "\\\\" + newFile.getName(), this.getId()).toString());
                            KafkaListeners.kafkaTemplate.send("import-data", new DataFile(this.getFolderPath().replaceAll("\\\\", "\\\\\\\\") + "\\\\" + newFile.getName(), this.getId()).toString());
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
