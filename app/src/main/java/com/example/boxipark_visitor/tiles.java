package com.example.boxipark_visitor;

import java.util.ArrayList;

public class tiles {
    //declare private data instead of public to ensure the privacy of data field of each class
    private String name;
    private String hometown;

    public tiles(String name, String hometown) {
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

    public static ArrayList<tiles> getUsers() {
        ArrayList<tiles> tiles1 = new ArrayList<tiles>();
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));
        tiles1.add(new tiles("Park Brewing IPA", "$ 7"));

        return tiles1;
    }

}
