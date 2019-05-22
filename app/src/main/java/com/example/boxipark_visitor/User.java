package com.example.boxipark_visitor;

import java.util.ArrayList;

public class User {

    //declare private data instead of public to ensure the privacy of data field of each class
    private String name;
    private String hometown;

    public User(String name, String hometown) {
        this.name = name;
        this.hometown = hometown;
    }

    //retrieve user's name
    public String getName(){
        return name;
    }

    //retrieve users' hometown
    public String getHometown(){
        return hometown;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("Park Brewing IPA", "$ 7"));
        users.add(new User("Park Brewing IPA", "$ 7"));
        users.add(new User("Park Brewing IPA", "$ 7"));
        users.add(new User("Park Brewing IPA", "$ 7"));
        users.add(new User("Park Brewing IPA", "$ 7"));
        users.add(new User("Park Brewing IPA", "$ 7"));

        return users;
    }
}