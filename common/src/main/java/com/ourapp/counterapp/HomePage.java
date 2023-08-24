package com.ourapp.counterapp;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


import java.util.Date;
import java.util.ArrayList;

public class HomePage {
    public static void displayHomePage(User loggedInUser){
        //starts making your app page but doesnt actually show it till you do homePage.show();
        Form homePage = new Form("Todays Calories");
        homePage.setLayout(new BoxLayout(BoxLayout.Y_AXIS)); // Vertical layout

        // Create and style the "Hello" label
        Label hello = new Label("Hello " + loggedInUser.getUserName());
        hello.getUnselectedStyle().setFgColor(0x000000);
        hello.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        hello.getUnselectedStyle().setMargin(10, 0, 10, 0); // Adding some margin

        // Add the "Hello" label to the form
        homePage.add(hello);

        // Add the greeting container to the form


        Container breakfastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<String> breakfastContentList = getBreakfast(loggedInUser);
        addSection(breakfastContainer, "Breakfast", breakfastContentList, e -> AddMealPage.addBreakfast(loggedInUser));



        homePage.add(breakfastContainer);

        homePage.show();
    }

    public static void addSection(Container parent, String title, ArrayList<String> contentList, ActionListener buttonListener) {
        Label titleLabel = new Label(title);
        titleLabel.getUnselectedStyle().setFgColor(0x000000);
        titleLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));

        parent.add(titleLabel);

        for (String content : contentList) {
            Label contentLabel = new Label(content);
            contentLabel.getUnselectedStyle().setFgColor(0x333333);
            parent.add(contentLabel);
        }

        if (buttonListener != null) {
            Button addButton = new Button("Add " + title);
            addButton.addActionListener(buttonListener);
            parent.add(addButton);
        }
    }
    public static ArrayList<String> getBreakfast(User loggedInUser){
        ArrayList<String> breakfastContentList = new ArrayList<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        ArrayList<Meal> meallist =  Meal.getMealFromDb(formattedDate, loggedInUser);

        System.out.println("meallist: " + meallist);
        for (Meal meal : meallist) {
            if(meal.getMealType().equals("BREAKFAST")) {
                breakfastContentList.add(String.valueOf(meal.getMealName()+" Cal:"+meal.getMealCal()));
            }

        }
        System.out.println(breakfastContentList.toString());
        return breakfastContentList;
    }

}
