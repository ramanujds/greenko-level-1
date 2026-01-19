package com.payease.app.model;

public class UserProfile extends Profile {

    private String fullName;



    public UserProfile(int userId, String fullName, String email, String phoneNo) {
        super(userId,email,phoneNo);
        this.fullName = fullName;
    }

    @Override
    public void showDetails(){
        System.out.println("User Profile details : ..");
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }


}
