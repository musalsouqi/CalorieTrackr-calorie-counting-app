package com.ourapp.counterapp;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;

public class HomePage {
    public static void displayHomePage(User loggedInUser){
        //starts making your app page but doesnt actually show it till you do homePage.show();
        Form homePage = new Form("Page Title", BoxLayout.y());

        //creates regular text
        Label hello = new Label("Hello " + loggedInUser.getUserName());
        //initiates a text field but doesnt actually do anything till you call it useing homepage.add(newTextField);
        TextField newTextField = new TextField("", "text hint" , 20, TextArea.ANY);
        //initiates a button but doesnt actually add it till you do homepage.add(newButton);
        Button newButton = new Button("button text");

        //adds text created on line 11 to the page that i created on line 9
        homePage.add(hello);



        ArrayList<Meal> meals =  Meal.getMealFromDb("1", loggedInUser);
        if(meals.size()>=1) {
            Label test = new Label(String.valueOf(meals.get(0)));
            homePage.add(test);
        }

// Show the form after adding components
        homePage.show();


    }

}
