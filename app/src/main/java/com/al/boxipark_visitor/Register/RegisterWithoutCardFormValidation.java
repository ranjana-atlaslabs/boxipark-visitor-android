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
import com.al.boxipark_visitor.Register.RegisterWithoutCard.RegisterWithoutCard;


public class RegisterWithoutCardFormValidation extends RegisterWithoutCard {
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

    public static int emailValidation(String email, Context c) {
        String trimmedMail = email.trim();
        if (trimmedMail.equals("") || trimmedMail == null) {
            ShowPopup("Please enter an email", "Try Again", c);
            return 0;
        }
        else if(isValid(trimmedMail)==false)
        {
            ShowPopup("Invalid email", "Try Again", c);
            return 0;
        }
        else{
            return 1;
        }

    }
    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static int phoneValidation(String phoneNumber, Context c) {
        String trimmedPhone = phoneNumber.trim();
        if (trimmedPhone.equals("") || trimmedPhone == null) {
            ShowPopup("Please enter a mobile Number", "Try Again", c);
            return 0;
//        } else if (trimmedPhone.length() == 11) {
//            if (!trimmedPhone.substring(0, 1).equals("1")) {
//                ShowPopup("Invalid mobile number,should be 1 if 10 digits", "Try Again", c);
//                return 0;
//            } else {
//                return 1;
//            }
//        } else if (trimmedPhone.length() == 10) {
//            if (trimmedPhone.substring(0, 1).equals("0")) {
//                ShowPopup("Invalid mobile number,Cannot be 0 if 10 digits", "Try Again", c);
//                return 0;
//            } else {
//                return 1;
//            }

        } else {
            return 1;
        }

    }

    public static int passwordValidation(String password, Context c) {
        String trimmedPassword = password;
        if (trimmedPassword.equals("") || password == null) {
            ShowPopup("Please enter a password", "Try Again", c);
            return 0;
        } else if (trimmedPassword.length() < 8) {
            ShowPopup("Password length should be 8 characters long", "Try Again", c);
            return 0;
        } else {
            return 1;
        }

    }

    public static int nameValidation(String name, Context c) {
        String trimmedName = name.trim();
        if (trimmedName.equals("") || trimmedName == null) {
            ShowPopup("Enter a name", "Try Again", c);
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isNumeric(String strNum, Context c) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
