package com.ourapp.counterapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import java.util.function.Consumer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Meal {
    private String mealName, mealCal, mealDate;
    private String mealType;
    public interface MealsCallback {
        void onMealsReceived(ArrayList<Meal> meals);
    }
    public Meal(String mealName, String mealCal, String mealDate) {
        this.mealName = mealName;
        this.mealCal = mealCal;
        this.mealDate = mealDate;
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
        return
                "meal:" + mealName + ' ' +
                "Cal:" + mealCal ;
    }

    public static ArrayList<Meal> getMealFromDb(String date, User loggedInUser) {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        String user = loggedInUser.getUserName();
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/getmeal");
        r.setPost(true);
        r.addArgument("username", user);
        r.addArgument("date", date);

        r.addResponseListener(e -> {

            if (e.getResponseCode() == 200) {
                try {
                    String responseText = new String(r.getResponseData(), "UTF-8");
                    JSONParser parser = new JSONParser();
                    Map<String, Object> jsonMap = parser.parseJSON(new CharArrayReader(responseText.toCharArray()));

                    if (jsonMap.containsKey("data")) {
                        System.out.println("success");
                        ArrayList<Map<String, Object>> mealDataList = (ArrayList<Map<String, Object>>) jsonMap.get("data");
                        System.out.println("success");


                        for (Map<String, Object> mealData : mealDataList) {
                            String mealName = (String) mealData.get("mealName");
                            String mealCal = String.valueOf(mealData.get("mealCal"));
                            String mealDate = (String) mealData.get("date");

                            // Create a Meal object with the parsed data
                            Meal meal = new Meal(mealName, mealCal, mealDate);

                            // Add the meal to your list of meals
                            meals.add(meal);
                        }


                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(r);
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
