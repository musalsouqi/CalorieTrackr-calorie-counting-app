package com.ourapp.counterapp;

import com.codename1.io.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class LoginPage {
    private static String user;
    private static String pass;

    public static void loginPage() {
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl("http://Velo3-env.eba-heysjumt.us-west-2.elasticbeanstalk.com:8080/api/users");
        r.setPost(true); // Set this to true for POST reques
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
            r.addArgument("username", user);
            r.addArgument("password", pass);
            r.addResponseListener(response -> {
                String responseData = new String(r.getResponseData());
                if(responseData.equals("Login failed")){
                    Dialog.show("Login Failed!", "we will add something here", "OK", null);
                }else{
                    Dialog.show("Login Successfull!", "we will add something here", "OK", null);
                }



            });
            NetworkManager.getInstance().addToQueueAndWait(r);
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
