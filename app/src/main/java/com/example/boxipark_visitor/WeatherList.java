package com.example.boxipark_visitor;

import java.util.ArrayList;

public class WeatherList {
    private String day;
    private String type;
    private String food;

    public WeatherList(String day, String type, String food) {
        this.day = day;
        this.type = type;
        this.food=food;
    }


    public String getDay(){
        return day;
    }


    public String getType(){
        return type;
    }

    public String getFood(){
        return food;
    }

    public static ArrayList<WeatherList> getFoodOffer() {
        ArrayList<WeatherList> food = new ArrayList<WeatherList>();
        food.add(new WeatherList("Tuesday","sunny","icecream"));
        food.add(new WeatherList("Wednesday","sunny","icecream"));
        food.add(new WeatherList("Thursday","sunny","icecream"));
        food.add(new WeatherList("Friday","sunny","icecream"));
        food.add(new WeatherList("Saturday","sunny","icecream"));
        food.add(new WeatherList("Sunday","sunny","icecream"));


        return food;
    }
}
