package com.ourapp.counterapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;


public class Meal {
    private String mealName, mealCal, mealDate;
    private String mealType;
    public interface MealsCallback {
        void onMealsReceived(ArrayList<Meal> meals);
    }
    public Meal(String mealName, String mealCal, String mealDate, String mealType) {
        this.mealName = mealName;
        this.mealCal = mealCal;
        this.mealDate = mealDate;
        this.mealType = mealType;
    }
    public static void addMealToDb(User loggedInUser, String mealName, MealType mealType, String mealCal) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        String user = loggedInUser.getUserName();
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/addmeal");
        r.setPost(true);
        r.addArgument("username", user);
        r.addArgument("mealName", mealName);
        r.addArgument("mealType", String.valueOf(mealType));
        r.addArgument("mealCal", String.valueOf(mealCal));
        r.addArgument("date", formattedDate);

        r.addResponseListener(e -> {
            String response = new String(r.getResponseData());
            if ("Meal added successfully".equals(response)) {

            } else {

            }
        });
        NetworkManager.getInstance().addToQueue(r);
    }

//    @Override
////    public String toString() {
////        return
////                "meal:" + mealName + ' ' +
////                "Cal:" + mealCal + ' ';
////    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealName='" + mealName + '\'' +
                ", mealCal='" + mealCal + '\'' +
                ", mealDate='" + mealDate + '\'' +
                ", mealType='" + mealType + '\'' +
                '}';
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
                            String mealType = (String) mealData.get("mealType");
                            String mealDate = (String) mealData.get("date");

                            Meal meal = new Meal(mealName, mealCal, mealDate, mealType);

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

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}
