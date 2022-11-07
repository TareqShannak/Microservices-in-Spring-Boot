package com.example.common.model;

public class DataLine {

    private int formId;

    private String data;

    public DataLine() {
    }

    public DataLine(int formId, String data) {
        this.formId = formId;
        this.data = data;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"formId\":" + formId +
                ", \"data\":\"" + data + '\"' +
                '}';
    }
}
