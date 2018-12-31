package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.User;

public class MySession {

    public static User loggedInUser;
    public static boolean isUserLoggedIn(){
        return loggedInUser != null;
    }
}
