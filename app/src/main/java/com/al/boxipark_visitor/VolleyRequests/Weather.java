package com.al.boxipark_visitor.VolleyRequests;

import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class Weather {
    final String[] res = new String[1];
    public String getWeather(Context context)
    {

        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen.

                res[0] =response;
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                res[0]=String.valueOf(error);
            }
        });

        ExampleRequestQueue.add(ExampleStringRequest);



return res[0];

    }



}
