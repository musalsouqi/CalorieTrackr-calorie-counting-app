package com.ourapp.counterapp;


import com.codename1.system.Lifecycle;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

public class Main extends Lifecycle {

    @Override
    public void runApp() {
        LoginPage.loginPage();
    }

    private void hello() {
        Dialog.show("Sorry under construction", "we will add something here", "OK", null);
    }

}
