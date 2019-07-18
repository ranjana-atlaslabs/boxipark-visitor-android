package com.al.boxipark_visitor.Adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Lists.WeatherList;
import com.al.boxipark_visitor.Other.ScreenSize;

import java.util.ArrayList;

public class CustomWeatherAdapter extends ArrayAdapter<WeatherList> {
    ImageView jAdaptImage;
    public CustomWeatherAdapter(Context context, ArrayList<WeatherList> food) {
        super(context, 0, food);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_item, parent, false);
        }

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;


        ScreenSize s=new ScreenSize();
        float size=s.size(convertView.getContext());




        // Get the data item for this position
        WeatherList foods = getItem(position);

        // Lookup view for data population
        TextView jday = convertView.findViewById(R.id.aDay);
ImageView jTypeView= convertView.findViewById(R.id.aTypeView);
        ImageView jFoodView= convertView.findViewById(R.id.aFoodView);
        jday.setTextSize(size);
       // TextView tvHome = (TextView) convertView.findViewById(R.id.aPrice);
        // jAdaptImage=(ImageView)convertView.findViewById(R.id.aAdaptImage);
        //jAdaptImage.getLayoutParams().height=0;
        // Populate the data into the template view using the data object
        jday.setText(foods.getDay());
        jTypeView.setImageResource(foods.getType());
        jFoodView.setImageResource(foods.getFood());
       // tvHome.setText(user.getHometown());
        // Return the completed view to render on screen
        return convertView;
    }
}
