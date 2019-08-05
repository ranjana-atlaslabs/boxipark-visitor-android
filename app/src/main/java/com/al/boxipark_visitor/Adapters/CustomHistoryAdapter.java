package com.al.boxipark_visitor.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.al.boxipark_visitor.Lists.MenuProducts;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.TransactionHistory.HistoryModels.HistoryDataBase;

import java.util.ArrayList;

public class CustomHistoryAdapter extends ArrayAdapter<HistoryDataBase> {

    TextView jStore, jPoints, jId, jDate;
    Context c;

    public CustomHistoryAdapter(Context context, int adapter_history, ArrayList<HistoryDataBase> menuProducts) {

        super(context, 0, menuProducts);

        c = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_history, parent, false);
        }

        // Get the data item for this position
        HistoryDataBase menuProducts = getItem(position);
        ScreenSize s = new ScreenSize();
        float size = s.size(getContext());

        FontsSet f = new FontsSet();
        // Lookup view for data population

        //casting
        jStore = convertView.findViewById(R.id.aStore);
        jId = convertView.findViewById(R.id.aTId);
        jDate = convertView.findViewById(R.id.aTDate);
        jPoints = convertView.findViewById(R.id.aPoints);

        //Styles
        jStore.setTypeface(f.Book(c));
        jPoints.setTypeface(f.Medium(c));
        jDate.setTypeface(f.Book(c));
        jId.setTypeface(f.Book(c));


        //Size
        jStore.setTextSize((float) (size * 1));
        jPoints.setTextSize((float) (size * 1));
        jId.setTextSize((float) (size * 0.6));
        jDate.setTextSize((float) (size * 0.6));

        HistoryDataBase h = new HistoryDataBase(menuProducts.getStoreName(), menuProducts.getPoint(), menuProducts.getId(), menuProducts.getDate());
        jStore.setText(h.getStoreName());
        jPoints.setText(h.getPoint() + " Points");
        if (h.getPoint().substring(0, 1).equals("+")) {
            jPoints.setTextColor(ContextCompat.getColor(c, R.color.colorReward));
        } else {
            jPoints.setTextColor(ContextCompat.getColor(c, R.color.colorRedeem));
        }


        jId.setText("Transaction ID : " + h.getId());
        jDate.setText(h.getDate());


        return convertView;
    }


}
