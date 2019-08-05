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
    TextView jType, jTemp, jDesc, jTue, jWed, jThu, jFri, jSat, jSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_modified);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    casting();
    style();
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
    public void casting()
    {
        jType= findViewById(R.id.aMWeatherType);
        jTemp= findViewById(R.id.aMWeatherTemp);
        jDesc= findViewById(R.id.aMWeatherDescription);
        jTue= findViewById(R.id.aTue);
        jWed= findViewById(R.id.aWed);
        jThu= findViewById(R.id.aThu);
        jFri= findViewById(R.id.aFri);
        jSat= findViewById(R.id.aSat);
        jSun= findViewById(R.id.aSun);
    }
    public void style()
    {

        FontsSet f=new FontsSet();
        Typeface m=f.Book(this);
        ScreenSize s=new ScreenSize();
        float size=s.size(this);

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
    }
}
