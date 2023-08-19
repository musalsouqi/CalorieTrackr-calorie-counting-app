package com.ourapp.counterapp;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class HomePage {
    public static void displayHomePage(){
        //starts making your app page but doesnt actually show it till you do homePage.show();
        Form homePage = new Form("Page Title", BoxLayout.y());
        //initiates a text field but doesnt actually do anything till you call it useing homepage.add(newTextField);
        TextField newTextField = new TextField("", "textbox", 20, TextArea.ANY);
        //initiates a button but doesnt actually add it till you do homepage.add(newButton);
        Button newButton = new Button("button text");

        //adds the textfield i made on line 11 to the form i made on line 9
        homePage.add(newTextField);

        //adds the button i made on line 13 to the form i made on line 9
        homePage.add(newButton);

        //this shows the page when called.. so if i wanted to call it in the main class i would do HomePage.displayHomePage()
        //because homePage.show(); is in this class.
        homePage.show();
    }
}
