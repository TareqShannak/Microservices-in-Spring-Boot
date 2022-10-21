package com.example.common.model;

public class DataFile {

    private String path;

    private int formId;

    public DataFile() {
    }

    public DataFile(String path, int formId) {
        this.path = path;
        this.formId = formId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
}
