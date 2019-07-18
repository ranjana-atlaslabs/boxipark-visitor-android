package com.al.boxipark_visitor.Register.RegisterWithCard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.RegisterWithoutCard;
import com.al.boxipark_visitor.Other.BlurBuilder;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

public class RegisterWithCard extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ImageView imageViewBackground= findViewById(R.id.aBackRegister);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.backgrn);
        Bitmap blurredBitmap = BlurBuilder.blur( this, icon );

        imageViewBackground.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );
        //Casting
        TextView jSign2= findViewById(R.id.aSignUp2);
        TextView jSign2Text= findViewById(R.id.aSignupText);
        EditText jUsername= findViewById(R.id.aUserNameSign);
        EditText jRegNo= findViewById(R.id.aRegistrationNo);
        EditText jPassword= findViewById(R.id.aPasswordSign2);
        Button jSignup= findViewById(R.id.aSign2B);

        FontsSet f=new FontsSet();
        jSign2.setTypeface(f.Book(this));
        jUsername.setTypeface(f.Book(this));
        jRegNo.setTypeface(f.Book(this));
        jPassword.setTypeface(f.Book(this));
        jSignup.setTypeface(f.Book(this));

        //Set sizes
        ScreenSize s=new ScreenSize();
        float size=s.size(this);
        jSign2Text.setTextSize((float) (size*0.6));
        jUsername.setTextSize((float) (size*0.6));
        jRegNo.setTextSize((float) (size*0.6));
        jPassword.setTextSize((float) (size*0.6));
        jSign2.setTextSize((float) (size*0.6));
        jSignup.setTextSize((float) (size*0.8));


        jSign2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singup2();
                    }
                }
        );

    }
    public void singup2()
    {
        Intent intent = new Intent(this, RegisterWithoutCard.class);
        startActivity ( intent );

    }



}
