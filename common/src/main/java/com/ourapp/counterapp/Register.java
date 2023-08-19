package com.ourapp.counterapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

public class Register {
    public static void registration() {
        Form registrationPage = new Form("Registration", BoxLayout.y());
        TextField usernameField = new TextField("", "Username", 20, TextArea.ANY);
        TextField passwordField = new TextField("", "Password", 20, TextArea.PASSWORD);
        TextField emailField = new TextField("", "Email", 20, TextArea.EMAILADDR);
        Button registerButton = new Button("Register");

        registrationPage.add(usernameField);
        registrationPage.add(passwordField);
        registrationPage.add(emailField);
        registrationPage.add(registerButton);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            ConnectionRequest r = new ConnectionRequest();
            r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/register");
            r.setPost(true);
            r.addArgument("username", username);
            r.addArgument("password", password);
            r.addArgument("email", email);

            r.addResponseListener(response -> {
                String responseData = new String(r.getResponseData());
                if (responseData.equals("Registration successful")) {
                    Dialog.show("Registration Successful", "You can now log in.", "OK", null);
                    LoginPage.loginPage();
                } else {
                    Dialog.show("Registration Failed", responseData, "OK", null);
                }
            });

            NetworkManager.getInstance().addToQueue(r); // Asynchronous networking
        });

        registrationPage.show();
    }
}
