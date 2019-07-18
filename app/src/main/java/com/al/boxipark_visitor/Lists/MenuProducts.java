package com.al.boxipark_visitor.Lists;

import java.util.ArrayList;

public class MenuProducts {

    //declare private data instead of public to ensure the privacy of data field of each class
    private String name;
    private String price;
    private String desc;


    //retrieve user's name
    public String getName(){
        return name;
    }

    //retrieve users' hometown
    public String getHometown(){
        return price;
    }

    public String getDescn(){
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHometown(String price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}