package com.al.boxipark_visitor.Other;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenSize {

    public float size(Context c)
    {
        float size=0;
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        float ratio=height/width;
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+ratio);

        if(ratio>=1.9)
        {
            size=(width/100)+(height/250);
        }
        else if(ratio>=1.8)
        {
            size=(width/100)+(height/250);
        }
        else if(ratio>=1.779)
        {
            size=(width/100)+(height/60);
        }

        else if(ratio>=1.77)
        {
            size=(width/100)+(height/140);
        }
        else if(ratio>=1.75)
        {
            size=(width/100)+(height/60);
        }
        else if(ratio>=1.7)
        {
            size=(width/100)+(height/120);
        }
        else if(ratio>=1.66)
        {
            size=(width/100)+(height/280);
        }
        else if(ratio>=1.64)
        {
            size=(width/100)+(height/120);
        }
        else if(ratio>=1.6)
        {
            size=(width/100)+(height/250);
        }
        else if(ratio>=1.5)
        {
            size=(width/100)+(height/80);
        }
        else if(ratio>=1.3)
        {
            size=(width/100)+(height/150);
        }

        return size;
    }

    public float ratio(Context c)
    {
        float size=0;
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        float ratio=height/width;

        return ratio;
    }
}
