package com.al.boxipark_visitor.Adapters;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.*;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Lists.MenuProducts;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

public class CustomProductAdapter extends ArrayAdapter<MenuProducts> implements Html.ImageGetter {
    ImageView jAdaptImage;
    Context c;
    private TextView tvName;
    public CustomProductAdapter(Context context, ArrayList<MenuProducts> menuProducts) {

        super(context, 0, menuProducts);

        c=context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the data item for this position
        MenuProducts menuProducts = getItem(position);
        ScreenSize s=new ScreenSize();
        float size=s.size(getContext());

        FontsSet f=new FontsSet();
        // Lookup view for data population
       tvName = convertView.findViewById(R.id.txtview);
        TextView tvPrice = convertView.findViewById(R.id.aPrice);
        TextView jDollar= convertView.findViewById(R.id.aDollar);

        tvName.setTypeface(f.Book(convertView.getContext()));
        tvPrice.setTypeface(f.Bold(convertView.getContext()));
        jDollar.setTypeface(f.Bold(convertView.getContext()));


        tvName.setTextSize((float) (size*0.75));


        tvPrice.setTextSize((float) (size*0.75));
        jDollar.setTextSize((float) (size*0.75));



        //tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_draw, 0);
        tvPrice.setText(menuProducts.getHometown());
        if(tvPrice.getText().equals(""))
        {
            jDollar.setText(" ");
        }

        String source = menuProducts.getName()+
                "&nbsp&nbsp<img src=\"http://cdn.onlinewebfonts.com/svg/img_515098.png\">";

        Spanned spanned = Html.fromHtml(source, this, null);

        tvName.setText(spanned);

      /*  String text2 = menuProducts.getName() + " ℹ";
              //  String text2 = menuProducts.getName() +  Html.fromHtml("<img src=\"http://cdn.onlinewebfonts.com/svg/img_515098.png\">");
        Spannable spannable = new SpannableString(text2);

        spannable.setSpan(new ForegroundColorSpan(Color.BLACK), menuProducts.getName().length(), (menuProducts.getName() + "ⓘ").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvName.setText(spannable, TextView.BufferType.SPANNABLE);

        //tvName.setText(menuProducts.getName() );*/






        /*SpannableStringBuilder ssb = new SpannableStringBuilder( );
        Bitmap smiley = BitmapFactory.decodeResource( convertView.getResources(), R.drawable.right_draw );
        ssb.setSpan( new ImageSpan( smiley ), ssb.length()-1,  ssb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE );
        tvName.setText( ssb, TextView.BufferType.SPANNABLE );*/
       //tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.infob, 0, 0, 0);

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        ScreenSize s=new ScreenSize();
        float ratio=s.ratio(getContext());
        if(ratio>=1.3&&ratio<=1.4)
        {
            Drawable empty = getContext().getResources().getDrawable(R.drawable.right_tab);
            d.addLevel(0, 0, empty);
            d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight()+5);
        }
        else
        {
            Drawable empty = getContext().getResources().getDrawable(R.drawable.right_draw);
            d.addLevel(0, 0, empty);
            d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight()+5);
        }





        return d;
    }



}
