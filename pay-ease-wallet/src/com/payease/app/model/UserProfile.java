package com.payease.app.model;

public class UserProfile {

    private int userId;
    private String fullName;
    private String email;
    private String phoneNo;

    public UserProfile(int userId, String fullName, String email, String phoneNo) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
