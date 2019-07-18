package com.al.boxipark_visitor.VolleyRequests;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import com.al.boxipark_visitor.R;

class ImageGetter implements Html.ImageGetter {
   public Context c;
    public Drawable getDrawable(String source) {
        int id;

        if (source.equals("stack.jpg")) {
            id = R.drawable.infob;
        }
        else if (source.equals("overflow.jpg")) {
            id = R.drawable.infob;
        }
        else {
            return null;
        }

        Drawable d = c.getResources().getDrawable(id);
        d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
        return d;
    }


}