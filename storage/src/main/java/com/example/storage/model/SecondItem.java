package com.example.storage.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecondItem {

    private String username;

    @Id
    private Long id;

    private String accessCode;

    private String recoveryCode;

    private String firstName;

    private String lastName;

    private String department;

    private String location;

    public SecondItem() {
    }

    public SecondItem(String username, Long id, String accessCode, String recoveryCode, String firstName, String lastName, String department, String location) {
        this.username = username;
        this.id = id;
        this.accessCode = accessCode;
        this.recoveryCode = recoveryCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SecondItem{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", accessCode='" + accessCode + '\'' +
                ", recoveryCode='" + recoveryCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
