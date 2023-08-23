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

        //adds the textfield i made on line 14 to the form i made on line 9
        homePage.add(newTextField);
        //todo test code please dont delete below here
//        ArrayList<Meal> refrence = Meal.getMealFromDb("1",MealType.BREAKFAST,loggedInUser);
//
//        Meal onemeal = refrence.get(1);
//        Label test = new Label(onemeal.toString());
//        homePage.add(test);
        //adds the button i made on line 16 to the form i made on line 9
        homePage.add(newButton);

        // the following code creates a listener that waits for the button i made earlier to
        // be pressed newButton.addActionListener(e -> this part of the code is what gets executed if the buttin is pressed);

        //this shows the page when called.. so if i wanted to call it in the main class i would do HomePage.displayHomePage()
        //because homePage.show(); is in this class.
        homePage.show();


    }

}
