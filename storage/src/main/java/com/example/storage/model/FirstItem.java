package com.example.storage.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirstItem {

    private String loginEmail;

    @Id
    private long id;

    private String oneTimePassword;

    private String recoveryCode;

    private String firstName;

    private String lastName;

    private String department;

    private String location;

    public FirstItem() {
    }

    public FirstItem(String loginEmail, long id, String oneTimePassword, String recoveryCode, String firstName, String lastName, String department, String location) {
        this.loginEmail = loginEmail;
        this.id = id;
        this.oneTimePassword = oneTimePassword;
        this.recoveryCode = recoveryCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
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
        return "FirstItem{" +
                "loginEmail='" + loginEmail + '\'' +
                ", id=" + id +
                ", oneTimePassword='" + oneTimePassword + '\'' +
                ", recoveryCode='" + recoveryCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
