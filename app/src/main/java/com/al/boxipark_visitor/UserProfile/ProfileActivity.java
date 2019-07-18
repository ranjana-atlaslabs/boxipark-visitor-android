package com.al.boxipark_visitor.UserProfile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.al.boxipark_visitor.Activity.UploadPhoto;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.UserInformation;

public class ProfileActivity extends AppCompatActivity {
    TextView jEdit,jPrivate,jFullname,jEmail,jPhone,jReset,jCurrent,jNew,jConfirm;
    EditText jNameText,jEmailText,jPhoneText,jCurrentP,jNewP,jConfirmP;
    Button jSave,jcancel;
public  UserData user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        casting();
        setTextSize();
        setTextType();
        Intent intent = getIntent();
        editBtnClick();

        cancelBtnClick();
        saveBtnClick();
        user = (UserData) intent.getSerializableExtra("USER");
       jNameText.setText(user.fields.firstName);
       jEmailText.setText(user.fields.email);
       jPhoneText.setText("+"+user.fields.mobilePhone);
    }

    public void setTextType()
    {
        FontsSet f=new FontsSet();
        Typeface t=f.Book(this);
        //Casting
        setTextSize();


        jEdit.setTypeface(t);

        jPrivate.setTypeface(t);
        jFullname.setTypeface(t);
        jEmail.setTypeface(t);
        jPhone.setTypeface(t);
        jReset.setTypeface(t);
        jCurrent.setTypeface(t);
        jConfirm.setTypeface(t);
        jNameText.setTypeface(t);
        jEmailText.setTypeface(t);
        jPhoneText.setTypeface(t);
        jCurrentP.setTypeface(t);
        jNew.setTypeface(t);
        jNewP.setTypeface(t);
        jConfirmP.setTypeface(t);
        jSave.setTypeface(t);
        jcancel.setTypeface(t);
    }
    public void setTextSize()
    {
    ScreenSize s=new ScreenSize();
    float sizei= (s.size(this));
    jEdit.setTextSize((float) (sizei*0.6));
    jPrivate.setTextSize((float) (sizei*0.7));
    jFullname.setTextSize((float) (sizei*0.7));
    jEmail.setTextSize((float) (sizei*0.7));
    jPhone.setTextSize((float) (sizei*0.7));
    jReset.setTextSize((float) (sizei*0.7));
    jCurrent.setTextSize((float) (sizei*0.7));
    jNew.setTextSize((float) (sizei*0.7));
    jConfirm.setTextSize((float) (sizei*0.7));


    jNameText.setTextSize((float) (sizei*0.65));
    jEmailText.setTextSize((float) (sizei*0.65));
    jPhoneText.setTextSize((float) (sizei*0.65));
    jCurrentP.setTextSize((float) (sizei*0.65));
    jNewP.setTextSize((float) (sizei*0.65));
    jConfirmP.setTextSize((float) (sizei*0.65));

    jSave.setTextSize((float) (s.size(this)*0.8));
    jcancel.setTextSize((float) (s.size(this)*0.8));
}
    public void editBtnClick()
    {
        jEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), UploadPhoto.class);
                        intent.putExtra("USER",user);
                        startActivity(intent);
                    }
                }
        );
    }
    public void cancelBtnClick()
    {
        jcancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    public void saveBtnClick()
    {
        jSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
    @SuppressLint("WrongViewCast")
    public void casting()
    {
        jEdit= findViewById(R.id.aEditPhoto);
        jPrivate= findViewById(R.id.aPInfo);
        jFullname= findViewById(R.id.aFullName);
        jEmail= findViewById(R.id.aEmail);
        jPhone= findViewById(R.id.aPhone);
        jReset= findViewById(R.id.aReset);
        jCurrent= findViewById(R.id.aCurrentP);
        jNew= findViewById(R.id.aNewP);
        jConfirm= findViewById(R.id.aConfirmP);

        jNameText= findViewById(R.id.aFullNameText);
        jEmailText= findViewById(R.id.aEmailText);
        jPhoneText= findViewById(R.id.aPhoneText);
        jCurrentP= findViewById(R.id.aCurrentPText);
        jNewP= findViewById(R.id.aNewPText);
        jConfirmP= findViewById(R.id.aConfirmPText);

        jSave= findViewById(R.id.aSave);
        jcancel= findViewById(R.id.aCancel);

    }
}
