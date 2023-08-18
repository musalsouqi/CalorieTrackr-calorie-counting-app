package com.ourapp.counterapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;


public class LoginPage {
    private static String user;
    private static String pass;

    public static void loginPage() {
        Form two = new Form("Login", BoxLayout.y());
        TextField userName = new TextField("", "Username", 20, TextArea.ANY);
        TextComponentPassword password = new TextComponentPassword();
        two.add(userName);
        two.add(password);
        Button submit = new Button("Submit");
        two.add(submit);

        submit.addActionListener(e -> {
            user = userName.getText();
            pass = password.getText();



            // Create a connection request to your web service
            ConnectionRequest request = new ConnectionRequest();
            // Make sure to use the full URL, including http/https
            request.setUrl("http://127.0.0.1:8080/api/login");
            // Use correct argument names based on your web service API
            request.addArgument("username", user);
            request.addArgument("password", pass);

            // Handle the response
            request.addResponseListener(res -> {
                String responseText = new String(request.getResponseData());
                Dialog.show(responseText, "we will add something here", "OK", null);
                // Process the response as needed, e.g., show a message or navigate to the next screen
            });

            // Send the request
            NetworkManager.getInstance().addToQueue(request);
        });

        two.show();
    }

    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }
}
