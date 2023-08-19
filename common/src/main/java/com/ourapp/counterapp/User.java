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

    public void addMeal(String mealName, MealType mealType, int mealCal) {
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/addmeal");
        r.setPost(true);
        r.addArgument("username", userName);
        r.addArgument("mealName", mealName);
        r.addArgument("mealType", String.valueOf(mealType));
        r.addArgument("mealCal", String.valueOf(mealCal));

        r.addResponseListener(e -> {
            String response = new String(r.getResponseData());
            if ("Meal added successfully".equals(response)) {

            } else {

            }
        });

        NetworkManager.getInstance().addToQueue(r);
    }

    public String getUserName() {
        return userName;
    }

    public String getUserID() {
        return userID;
    }
}