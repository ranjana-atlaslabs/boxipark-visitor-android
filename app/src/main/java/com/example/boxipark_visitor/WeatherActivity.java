package com.example.boxipark_visitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
ListView jWeatherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        jWeatherList=(ListView)findViewById(R.id.aWeatherList);
        populateUsersList();
        setListViewHeightBasedOnChildren(jWeatherList);

    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<WeatherList> arrayOfUsers = WeatherList.getFoodOffer();
        // Create the adapter to convert the array to views
        CustomWeatherAdapter adapter = new CustomWeatherAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView



        jWeatherList.setAdapter(adapter);

       // setListViewHeightBasedOnChildren(jWeatherList);

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
