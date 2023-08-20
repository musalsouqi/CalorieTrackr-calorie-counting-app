package com.ourapp.counterapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import java.util.ArrayList;

public class Meal {
    private String mealName, mealCal, mealDate;
    private MealType MealType;

    public Meal(String mealName, String mealCal, String mealDate, MealType mealType) {
        this.mealName = mealName;
        this.mealCal = mealCal;
        this.mealDate = mealDate;
        MealType = mealType;
    }
    public static void addMealToDb(User loggedInUser, String mealName, MealType mealType, String mealCal) {
        String user = loggedInUser.getUserName();
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/addmeal");
        r.setPost(true);
        r.addArgument("username", user);
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

    public static ArrayList<Meal> getMealFromDb(String date, MealType mealType,User loggedInUser){
        return null;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCal() {
        return mealCal;
    }

    public void setMealCal(String mealCal) {
        this.mealCal = mealCal;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    public com.ourapp.counterapp.MealType getMealType() {
        return MealType;
    }

    public void setMealType(com.ourapp.counterapp.MealType mealType) {
        MealType = mealType;
    }


}
