package com.ourapp.counterapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Meal {
    private String mealName, mealCal, mealDate;
    private MealType mealType;

    public Meal(String mealName, String mealCal, String mealDate, MealType mealType) {
        this.mealName = mealName;
        this.mealCal = mealCal;
        this.mealDate = mealDate;
        this.mealType = mealType;
    }
    public static void addMealToDb(User loggedInUser, String mealName, MealType mealType, String mealCal) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String user = loggedInUser.getUserName();
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/addmeal");
        r.setPost(true);
        r.addArgument("username", user);
        r.addArgument("mealName", mealName);
        r.addArgument("mealType", String.valueOf(mealType));
        r.addArgument("mealCal", String.valueOf(mealCal));
        r.addArgument("date", dtf.format(now));

        r.addResponseListener(e -> {
            String response = new String(r.getResponseData());
            if ("Meal added successfully".equals(response)) {

            } else {

            }
        });
        NetworkManager.getInstance().addToQueue(r);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealName='" + mealName + '\'' +
                ", mealCal='" + mealCal + '\'' +
                ", mealDate='" + mealDate + '\'' +
                '}';
    }

    public static ArrayList<Meal> getMealFromDb(String date, MealType mealType, User loggedInUser) {
        String user = loggedInUser.getUserName();
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/getmeal");
        r.setPost(true);
        r.addArgument("username", user);
        r.addArgument("date", date);

        ArrayList<Meal> meals = new ArrayList<>();

        r.addResponseListener(e -> {
            JSONParser parser = new JSONParser();
            try {
                String response = new String(r.getResponseData(), "UTF-8");
                System.out.println("Response JSON: " + response);

                Map<String, Object> jsonResponse = parser.parseJSON(new CharArrayReader(response.toCharArray()));
                List<Map<String, Object>> mealDataList = (List<Map<String, Object>>) jsonResponse.get("root");

                for (Map<String, Object> mealData : mealDataList) {
                    String mealName = (String) mealData.get("mealName");
                    String mealCal = (String) mealData.get("mealCal");
                    String mealDate = (String) mealData.get("date");
                    MealType mealTypeEnum = MealType.BREAKFAST;

                    Meal meal = new Meal(mealName, mealCal, mealDate, mealTypeEnum);
                    meals.add(meal);
                }
            } catch (IOException ex) {
                // Handle IOException
                ex.printStackTrace();
            }
        });

        NetworkManager.getInstance().addToQueue(r); // Asynchronous processing
        return meals;
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


}
