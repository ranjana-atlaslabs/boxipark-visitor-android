package com.example.boxipark_visitor;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomUsersAdapter extends ArrayAdapter<User> {
    ImageView jAdaptImage;
    public CustomUsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the data item for this position
        User user = getItem(position);

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.txtview);
        TextView tvHome = (TextView) convertView.findViewById(R.id.aPrice);
       // jAdaptImage=(ImageView)convertView.findViewById(R.id.aAdaptImage);
        //jAdaptImage.getLayoutParams().height=0;
        // Populate the data into the template view using the data object
        tvName.setText(user.getName());
        tvHome.setText(user.getHometown());
        // Return the completed view to render on screen
        return convertView;
    }
}