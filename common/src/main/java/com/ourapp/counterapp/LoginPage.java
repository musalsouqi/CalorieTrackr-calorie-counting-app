package com.ourapp.counterapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;


import static com.codename1.ui.CN.callSerially;

public class LoginPage {

    public static void loginPage() {

        Form two = new Form("Login", BoxLayout.y());
        TextField userName = new TextField("", "Username", 20, TextArea.ANY);
        TextComponentPassword password = new TextComponentPassword();
        two.add(userName);
        two.add(password);
        Button submit = new Button("Submit");
        Button createAccount = new Button("Create account");
        two.add(submit);
        two.add(createAccount);

        submit.addActionListener(e -> {
            String user = userName.getText();
            String pass = password.getText();
            checkUserCredentials(user,pass);
    });
        createAccount.addActionListener(e -> Register.registration());
        two.show();
    }


    public static void checkUserCredentials(String user, String pass){
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/users");
        r.setPost(true);  r.addArgument("username", user);
            r.addArgument("password", pass);

            r.addResponseListener(response -> {
                String responseData = new String(r.getResponseData());
                callSerially(() -> {
                    if (responseData.equals("Login failed")) {
                        Dialog.show("Login Failed!", "we will add something here", "OK", null);
                    } else {
                        User loggedInUser = new User(user,responseData);
                        HomePage.displayHomePage(loggedInUser);
                    }
                });
            });

            NetworkManager.getInstance().addToQueue(r);
        }

    }
