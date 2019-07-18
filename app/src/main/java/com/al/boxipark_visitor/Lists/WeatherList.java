package com.al.boxipark_visitor.Lists;

import com.al.boxipark_visitor.R;

import java.util.ArrayList;

public class WeatherList {
    private String day;
    private int type;
    private int food;

    public WeatherList(String day, int type, int food) {
        this.day = day;
        this.type = type;
        this.food=food;
    }


    public String getDay(){
        return day;
    }


    public int getType(){
        return type;
    }

    public int getFood(){
        return food;
    }

    public static ArrayList<WeatherList> getFoodOffer() {
        ArrayList<WeatherList> food = new ArrayList<WeatherList>();
        food.add(new WeatherList("Tuesday", R.drawable.sunny,R.drawable.beer));
        food.add(new WeatherList("Wednesday",R.drawable.cloud,R.drawable.tacoi));
        food.add(new WeatherList("Thursday",R.drawable.shower,R.drawable.cocktail));
        food.add(new WeatherList("Friday",R.drawable.thunder,R.drawable.icecreamw));
        food.add(new WeatherList("Saturday",R.drawable.rain,R.drawable.cocktail));
        food.add(new WeatherList("Sunday",R.drawable.sunny,R.drawable.tacoi));


        return food;
    }
}
