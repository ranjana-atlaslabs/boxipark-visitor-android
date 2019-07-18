package com.al.boxipark_visitor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.al.boxipark_visitor.Adapters.CustomWeatherAdapter;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.Lists.WeatherList;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
ListView jWeatherList;
TextView jTempView,jWeatherTextW,jWeatherDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

         //Casting
        jTempView= findViewById(R.id.aTempViewW);
        jWeatherTextW= findViewById(R.id.aWeatherTextW);
        jWeatherDescription= findViewById(R.id.aWeatherDescription);

        ScreenSize s=new ScreenSize();
        jTempView.setTextSize(s.size(this)*2);
        jWeatherTextW.setTextSize((float) (s.size(this)*0.8));
        jWeatherDescription.setTextSize((float) (s.size(this)*0.6));
        jWeatherList= findViewById(R.id.aWeatherList);
        populateUsersList();
        setListViewHeightBasedOnChildren(jWeatherList);

        ImageView jBack= findViewById(R.id.aMenuBackW);
        jBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<WeatherList> arrayOfUsers = WeatherList.getFoodOffer();
        // Create the adapter to convert the array to views
        CustomWeatherAdapter adapter = new CustomWeatherAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView



        jWeatherList.setAdapter(adapter);

        setListViewHeightBasedOnChildren(jWeatherList);

    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
