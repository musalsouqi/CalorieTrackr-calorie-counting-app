package com.ourapp.counterapp;


import com.codename1.io.NetworkManager;
import com.codename1.system.Lifecycle;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;

import java.io.IOException;

public class Main extends Lifecycle {

    @Override
    public void runApp() {
        NetworkManager.getInstance().addErrorListener(e -> {
            if (e.getError() instanceof IOException) {
                e.consume();
            }
        });
        LoginPage.loginPage();

    }

    private void hello() {
        Dialog.show("Sorry under construction", "we will add something here", "OK", null);
    }

}
