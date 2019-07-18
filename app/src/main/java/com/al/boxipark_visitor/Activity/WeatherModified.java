package com.al.boxipark_visitor.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

public class WeatherModified extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_modified);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FontsSet f=new FontsSet();
        Typeface m=f.Book(this);
        ScreenSize s=new ScreenSize();
        float size=s.size(this);

        TextView jType= findViewById(R.id.aMWeatherType);
        TextView jTemp= findViewById(R.id.aMWeatherTemp);
        TextView jDesc= findViewById(R.id.aMWeatherDescription);
        TextView jTue= findViewById(R.id.aTue);
        TextView jWed= findViewById(R.id.aWed);
        TextView jThu= findViewById(R.id.aThu);
        TextView jFri= findViewById(R.id.aFri);
        TextView jSat= findViewById(R.id.aSat);
        TextView jSun= findViewById(R.id.aSun);

        jTemp.setTypeface(m);
        jDesc.setTypeface(m);
        jTue.setTypeface(m);
        jTue.setTypeface(m);
        jWed.setTypeface(m);
        jThu.setTypeface(m);
        jFri.setTypeface(m);
        jSun.setTypeface(m);
        jSat.setTypeface(m);

        jType.setTypeface(f.Light(this));

        jType.setTextSize((size));
        jTemp.setTextSize((size*3));
        jDesc.setTextSize((float) (size*0.75));
        jTue.setTextSize((float) (size*0.8));
        jWed.setTextSize((float) (size*0.8));
        jThu.setTextSize((float) (size*0.8));
        jFri.setTextSize((float) (size*0.8));
        jSat.setTextSize((float) (size*0.8));
        jSun.setTextSize((float) (size*0.8));

    ImageView jBack= findViewById(R.id.aMBack);
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

   /* public void addTiles()
    {
        LinearLayout jWeatherParent=(LinearLayout)findViewById(R.id.aWeatherMainLayout);

        LinearLayout item=new LinearLayout(this);
        item.setOrientation(LinearLayout.HORIZONTAL);
        item.setPadding(0,0,0,1);
        item.setBackgroundColor(0x6494B1);
        item.setWeightSum(3);
        jWeatherParent.addView(item);

        TextView text=new TextView(this);
        text.setText("text");
        text.setGravity(Gravity.CENTER_VERTICAL);
        text.setBackgroundColor(0x87C8EE);
        item.addView(text);

        ImageView i=new ImageView(this);

    }*/
}
