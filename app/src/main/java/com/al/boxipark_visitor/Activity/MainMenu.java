package com.al.boxipark_visitor.Activity;

import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;

import android.os.Build;
import android.service.quicksettings.Tile;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;


import android.view.WindowManager;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import com.al.boxipark_visitor.R;

import java.util.Random;



public class MainMenu extends AppCompatActivity {
    LinearLayout vScroll;
    int in=0;

    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
            1.0f
    );
    FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
    );

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Casting
        vScroll = findViewById(R.id.aVScroll);
        Button b2 = findViewById(R.id.aB2);

        //create tiles
        TileCreator();

        b2.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        deleteItem();
                    }
                }
        );

    }
@RequiresApi(api = Build.VERSION_CODES.M)
public void TileCreator()
{
    int noOfTiles=5;
    int noOfRows=noOfTiles/2;
    if(noOfTiles%2==1)
    {
        noOfRows++;
    }


    for(int i=0;i<noOfRows;i++)
    {

        LinearLayout et=addLinear();
        if(noOfTiles%2==1)
        {
            if(i==noOfRows-1)
            {
                addItemLeft(et);
                addBlankRight(et);
            }
            else {
                addItemLeft(et);
                addItemRight(et);
            }
        }
        else
        {
            addItemLeft(et);
            addItemRight(et);
        }

    }
}


public void remove()
{
        if(vScroll.getChildCount()!=0)
        {
            vScroll.removeViewAt(vScroll.getChildCount()-1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createBlank(LinearLayout et1)
    {


        FrameLayout f=new FrameLayout(this);
        f.setBackground(new ColorDrawable(generateCol()));
        f.setLayoutParams(param);
        f.setRotation(10);
        f.setScaleY((float) 1.3);
        f.setPadding(55,0,0,0);
        et1.addView(f);
        ImageView i=new ImageView(this);
        f.addView(i);
        i.setImageResource(R.drawable.barnonalogon);
        i.setId(in);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(param1);
        i.setAdjustViewBounds(true);

        i.setScaleX((float) 0.7);
        i.setScaleY((float) 0.6);

        FrameLayout f1=new FrameLayout(this);
        f1.setBackground(new ColorDrawable(generateCol()));
        f1.setLayoutParams(param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        f1.setPadding(0,0,55,0);
        et1.addView(f1);

        ImageView ii=new ImageView(this);
        f1.addView(ii);
        ii.setId(in);
        ii.setImageResource(R.drawable.canolilogon);

        ii.setLayoutParams(param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);
        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);



    }

    public LinearLayout addLinear()
    {


        LinearLayout et=new LinearLayout(this);
        et.setOrientation(LinearLayout.HORIZONTAL);
        et.setWeightSum(2);
        et.setDuplicateParentStateEnabled(true);
        et.setLayoutParams(param);
        et.setRotation(-10);
        // et.setScaleX((float) 1.1);
        vScroll.addView(et);
        return et;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addItemLeft(LinearLayout et)
    {
       // remove();




        FrameLayout f=new FrameLayout(this);
        f.setBackground(new ColorDrawable(generateCol()));
        f.setLayoutParams(param);
        f.setRotation(10);
        f.setScaleY((float) 1.3);
        f.setPadding(55,0,0,0);
        et.addView(f);




        ImageView i=new ImageView(this);
        f.addView(i);
        i.setImageResource(R.drawable.barnonalogon);
        i.setId(in);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(param1);
        i.setAdjustViewBounds(true);

        i.setScaleX((float) 0.7);
        i.setScaleY((float) 0.6);
        in++;



        listners(i);




    }

   /* public void addBlankRight(LinearLayout et)
    {
        FrameLayout f1=new FrameLayout(this);
        //f1.setBackground(new ColorDrawable(generateCol()));
        f1.setLayoutParams(param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        f1.setPadding(0,0,55,0);
        et.addView(f1);

        ImageView Blank1=new ImageView(this);
        f1.addView(Blank1);
        ii.setId(in);
        Blank1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        ii.setImageResource(R.drawable.canolilogon);

        ii.setLayoutParams(param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);
        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        //createBlank();


        listners(ii);




        in++;
    }*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addItemRight(LinearLayout et)
    {

        FrameLayout f1=new FrameLayout(this);
        f1.setBackground(new ColorDrawable(generateCol()));
        f1.setLayoutParams(param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        f1.setPadding(0,0,55,0);
        et.addView(f1);

        ImageView ii=new ImageView(this);
        f1.addView(ii);
        ii.setId(in);
        ii.setImageResource(R.drawable.canolilogon);

        ii.setLayoutParams(param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);
        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        //createBlank();


        listners(ii);




        in++;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addBlankRight(LinearLayout et)
    {

        FrameLayout f1=new FrameLayout(this);
        //f1.setBackground(new ColorDrawable(Color.rgb(r,g,b)));
        f1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        f1.setLayoutParams(param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        f1.setPadding(0,0,55,0);
        et.addView(f1);

        ImageView ii=new ImageView(this);
        f1.addView(ii);
        ii.setId(in);
        ii.setImageResource(R.drawable.canolilogon);

        ii.setLayoutParams(param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);
        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        //createBlank();








    }
    public void listners(ImageView v)
    {
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(getApplicationContext(),String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
                        next(v.getId());
                    }
                }
        );
    }

    public void next(int id)
    {
        Intent intent;
        if(id%2==1)
        {
           // intent = new Intent(this, ProductMenu.class);
          //  Toast.makeText(getApplicationContext(),String.valueOf(id), Toast.LENGTH_SHORT).show();
        }
        else
        {
           // intent = new Intent(this, MapActivity.class);
           // Toast.makeText(getApplicationContext(),String.valueOf(id), Toast.LENGTH_SHORT).show();
        }

      //  startActivity ( intent );
    }
    public void deleteItem()
    {
        if(vScroll.getChildCount()!=0)
        {
            vScroll.removeViewAt(vScroll.getChildCount()-1);



        }
        if(vScroll.getChildCount()!=1&&in>0)
        {
           in=in-2;


        }

    }

    public int generateCol()
    {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        int randomColor = Color.rgb(r,g,b);
        return randomColor;
    }



}
