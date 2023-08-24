package com.ourapp.counterapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

public class AddMealPage {
public static void addBreakfast(User loggedInUser){
    Form addBreakfastForm = new Form();
    TextField mealName = new TextField("", "Meal", 20, TextArea.ANY);
    TextField mealCal = new TextField("", "Calories", 20, TextArea.ANY);
    Button addMeal = new Button("Add Meal");
    addBreakfastForm.add(mealName);
    addBreakfastForm.add(mealCal);
    addBreakfastForm.add(addMeal);

    addMeal.addActionListener(e -> {
        Meal.addMealToDb(loggedInUser, mealName.getText(), MealType.BREAKFAST, mealCal.getText());
        HomePage.displayHomePage(loggedInUser);
    });
    addBreakfastForm.show();
}
}
