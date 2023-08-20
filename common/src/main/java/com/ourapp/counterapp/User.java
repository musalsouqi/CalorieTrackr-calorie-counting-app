package com.ourapp.counterapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

public class User {
    private final String userName;
    private final String userID;

    public User(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }
}