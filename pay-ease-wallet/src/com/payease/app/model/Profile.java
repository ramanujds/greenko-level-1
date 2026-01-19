package com.payease.app.model;

public abstract class Profile {
    protected int userId;
    protected String email;
    protected String phoneNo;

    public Profile(int userId, String email, String phoneNo) {
        this.userId = userId;
        this.email = email;
        this.phoneNo = phoneNo;
    }
    public Profile(){

    }

    public abstract void showDetails();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Profile{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
