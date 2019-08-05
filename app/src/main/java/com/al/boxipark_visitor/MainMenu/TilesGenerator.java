package com.al.boxipark_visitor.MainMenu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import com.al.boxipark_visitor.MainMenu.MainMenuTilesCreate.CreateTiles;
import com.al.boxipark_visitor.Other.MenuTiles;
import com.al.boxipark_visitor.R;

public class TilesGenerator extends MenuActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void addLeft(LinearLayout et, RootObject rootObject)
    {

        MenuTiles c=new MenuTiles();
        FrameLayout f=new FrameLayout(context);
        f.setBackground(new ColorDrawable(c.returnCol(menuTileNumber)));
        f.setLayoutParams(CreateTiles.param);
        f.setRotation(10);
        f.setScaleY((float) 1.3);
        if(menuTileNumber ==0)
        {
            f.setPadding(55,60,0,0);
        }
        else if(menuTileNumber ==8)
        {
            f.setPadding(55,0,0,60);
        }
        else
        {
            f.setPadding(55,0,0,0);
        }

        et.addView(f);

        ImageView i=new ImageView(context);
        f.addView(i);
        i.setImageResource(c.returnImg(menuTileNumber));
        i.setId(menuTileNumber);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(CreateTiles.param1);


        i.setPadding(0,0,10,0);
        i.setAdjustViewBounds(true);

        if(menuTileNumber ==4)
        {
            i.setScaleX((float) 0.5);
            i.setScaleY((float)0.4);
        }
        else if(menuTileNumber ==0)
        {
            i.setScaleX((float) 0.65);
            i.setScaleY((float) 0.55);
        }
        else if(menuTileNumber ==6)
        {
            i.setScaleX((float) 0.55);
            i.setScaleY((float) 0.45);
        }
        else
        {
            i.setScaleX((float) 0.6);
            i.setScaleY((float) 0.5);
        }


        menuTileNumber++;

        if(menuTileNumber !=9) {
            listners(i,rootObject);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void addRight(LinearLayout et,RootObject rootObject)
    {
        MenuTiles c=new MenuTiles();
        FrameLayout f1=new FrameLayout(context);
        f1.setBackground(new ColorDrawable(c.returnCol(menuTileNumber)));
        f1.setLayoutParams(CreateTiles.param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        if(menuTileNumber ==1)
        {
            f1.setPadding(0,60,55,0);
        }
        else if(menuTileNumber ==9)
        {
            f1.setPadding(0,0,55,60);
        }
        else
        {
            f1.setPadding(0,0,55,0);
        }

        et.addView(f1);





        ImageView ii=new ImageView(context);
        f1.addView(ii);
        ii.setId(menuTileNumber);
        ii.setImageResource(c.returnImg(menuTileNumber));

        ii.setLayoutParams(CreateTiles.param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);


        if(menuTileNumber !=1)
        {
            ii.setScaleX((float) 0.55);
            ii.setScaleY((float) 0.45);
        }

        else
        {
            ii.setScaleX((float) 0.5);
            ii.setScaleY((float) 0.4);
        }

        menuTileNumber++;
        listners(ii,rootObject);

    }

    public void addRightBlank(LinearLayout et)
    {
        MenuTiles c=new MenuTiles();
        FrameLayout f1=new FrameLayout(this);
        f1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        f1.setLayoutParams(CreateTiles.param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        if(menuTileNumber ==1)
        {
            f1.setPadding(0,60,55,0);
        }
        else if(menuTileNumber ==9)
        {
            f1.setPadding(0,0,55,60);
        }
        else
        {
            f1.setPadding(0,0,55,0);
        }

        et.addView(f1);





        ImageView ii=new ImageView(this);
        f1.addView(ii);
        ii.setId(menuTileNumber);
        ii.setImageResource(c.returnImg(menuTileNumber));

        ii.setLayoutParams(CreateTiles.param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);

        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        menuTileNumber++;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createBlank()
    {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        LinearLayout et1=new LinearLayout(this);
        et1.setOrientation(LinearLayout.HORIZONTAL);
        et1.setWeightSum(2);
        et1.setDuplicateParentStateEnabled(true);

        et1.setRotation(-10);
        vScroll.addView(et1);

        ImageView Blank1=new ImageView(this);
        et1.addView(Blank1);
        Blank1.setId(menuTileNumber);
        Blank1.setImageResource(R.drawable.logo);
        Blank1.setRotation(10);
        Blank1.setScaleY(2);
        Blank1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        Blank1.setLayoutParams(param);

        ImageView Blank2=new ImageView(this);
        et1.addView(Blank2);
        Blank2.setId(menuTileNumber);
        Blank2.setImageResource(R.drawable.logo);
        Blank2.setRotation(10);
        Blank2.setScaleY(2);
        Blank2.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        Blank2.setLayoutParams(param);
    }
}
