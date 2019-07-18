package com.al.boxipark_visitor.MainMenu.MainMenuTilesCreate;

import android.content.Context;

import android.widget.FrameLayout;

import android.widget.LinearLayout;

import com.al.boxipark_visitor.MainMenu.MenuActivity;


public class CreateTiles
{
   public static LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
    );



    public static FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
    );
//    public void deleteItem()
//    {
//        if(vScroll.getChildCount()!=0)
//        {
//            vScroll.removeViewAt(vScroll.getChildCount()-1);
//
//
//
//        }
//        if(vScroll.getChildCount()!=1&&in>0)
//        {
//            in=in-2;
//
//
//        }
//
//    }



//    public void addRightBlank(LinearLayout et)
//    {
//        MenuTiles c=new MenuTiles();
//        FrameLayout f1=new FrameLayout(this);
//        f1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
//        f1.setLayoutParams(CreateTiles.param);
//        f1.setRotation(10);
//        f1.setScaleY((float) 1.3);
//        if(in==1)
//        {
//            f1.setPadding(0,60,55,0);
//        }
//        else if(in==9)
//        {
//            f1.setPadding(0,0,55,60);
//        }
//        else
//        {
//            f1.setPadding(0,0,55,0);
//        }
//
//        et.addView(f1);
//
//
//
//
//
//        ImageView ii=new ImageView(this);
//        f1.addView(ii);
//        ii.setId(in);
//        ii.setImageResource(c.returnImg(in));
//
//        ii.setLayoutParams(param1);
//        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        ii.setAdjustViewBounds(true);
//
//        ii.setScaleX((float) 0.7);
//        ii.setScaleY((float) 0.6);
//        in++;
//
//    }


//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void createBlank()
//    {
//        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                1.0f
//        );
//
//        LinearLayout et1=new LinearLayout(this);
//        et1.setOrientation(LinearLayout.HORIZONTAL);
//        et1.setWeightSum(2);
//        et1.setDuplicateParentStateEnabled(true);
//
//        et1.setRotation(-10);
//        vScroll.addView(et1);
//
//        ImageView Blank1=new ImageView(this);
//        et1.addView(Blank1);
//        Blank1.setId(in);
//        Blank1.setImageResource(R.drawable.logo);
//        Blank1.setRotation(10);
//        Blank1.setScaleY(2);
//        Blank1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
//        Blank1.setLayoutParams(param);
//
//        ImageView Blank2=new ImageView(this);
//        et1.addView(Blank2);
//        Blank2.setId(in);
//        Blank2.setImageResource(R.drawable.logo);
//        Blank2.setRotation(10);
//        Blank2.setScaleY(2);
//        Blank2.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
//        Blank2.setLayoutParams(param);
//
//
//
//
//    }
//


//    public void remove()
//    {
//        if(vScroll.getChildCount()!=0)
//        {
//            vScroll.removeViewAt(vScroll.getChildCount()-1);
//        }
//    }



}
