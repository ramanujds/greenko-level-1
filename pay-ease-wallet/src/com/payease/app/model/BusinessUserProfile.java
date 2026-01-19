package com.payease.app.model;

public class BusinessUserProfile extends Profile {


    private String businessName;
    private String location;
    private String gstn;

    public BusinessUserProfile(int userId, String email, String phoneNo, String businessName, String location, String gstn) {
        super(userId, email, phoneNo);
        this.businessName = businessName;
        this.location = location;
        this.gstn = gstn;
    }

    public void showDetails(){
        System.out.println("Blah.. Blah.. Blah..");
    }

}
