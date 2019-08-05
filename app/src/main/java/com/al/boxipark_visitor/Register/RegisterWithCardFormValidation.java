package com.al.boxipark_visitor.Register;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Register.RegisterWithCard.RegisterWithCard;

public class RegisterWithCardFormValidation extends RegisterWithCard
{
    static Dialog myDialog;

    public static void ShowPopup(String body, final String btnBody, Context c) {
        myDialog = new Dialog(c);
        myDialog.setContentView(R.layout.popup_alert);
        TextView jPopText = myDialog.findViewById(R.id.aPopupBody);
        Button jPopBtn = myDialog.findViewById(R.id.aPopupBtn);

        jPopBtn.setText(btnBody);

        FontsSet f = new FontsSet();
        Typeface t = f.Book(c);
        jPopText.setTypeface(t);
        jPopBtn.setTypeface(t);


        //screen resizing
        ScreenSize s = new ScreenSize();
        float size = s.size(c);
        jPopText.setTextSize((float) (size * 0.75));
        jPopBtn.setTextSize((float) (size * 0.75));


        jPopText.setText(body);
        jPopBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                }
        );
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public static int cardNumValidate(String cardNum,Context c)
    {
        String trimmedCard = cardNum.trim();
        if(trimmedCard.equals("")||trimmedCard==null)
        {
            ShowPopup("Please enter a card number","Try Again",c);
            return 0;
        }
        else
        {
            return 1;
        }
    }
    public static int regNumberValidate(String regNum,Context c)
    {
        String trimmedReg = regNum.trim();
        if(trimmedReg.equals("")||trimmedReg==null)
        {
            ShowPopup("Please enter a registration number","Try Again",c);
            return 0;
        }
        else
        {
            return 1;
        }
    }






}
