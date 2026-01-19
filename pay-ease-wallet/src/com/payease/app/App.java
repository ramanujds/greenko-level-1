package com.payease.app;


import com.payease.app.model.BusinessUserProfile;
import com.payease.app.model.Profile;
import com.payease.app.model.UserProfile;
import com.payease.app.model.Wallet;

public class App {

    static void main(String[] args) {

        Profile user1 = new UserProfile(101,"ABC Tech","abc@yahoo.com",
                "888888888");
        user1.showDetails();

//        Wallet wallet1 = new Wallet(999,1000,user1);
//
//        System.out.println(user1);
//        System.out.println(wallet1);

        // Validators


    }
}
