package com.payease.app;


import com.payease.app.model.Profile;
import com.payease.app.model.UserProfile;
import com.payease.app.model.Wallet;

public class App {

    static void main(String[] args) {

        Profile user1 = new UserProfile(101,"Harsh Kumar","harsh@yahoo.com",
                "8998899999");
        user1.showDetails();

        Wallet wallet1 = new Wallet(999,1000,user1);

        System.out.println(user1);
        System.out.println(wallet1);

        // Validators


    }
}
