package com.ourapp.counterapp;



import com.codename1.system.Lifecycle;


public class Main extends Lifecycle {

    @Override
    public void runApp() {
        LoginPage.loginPage();
    }
}
