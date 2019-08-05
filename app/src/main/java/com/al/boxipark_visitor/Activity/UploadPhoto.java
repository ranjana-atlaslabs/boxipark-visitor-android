package com.al.boxipark_visitor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.UserProfile.ProfileActivity;

public class UploadPhoto extends AppCompatActivity {
    public  UserData user;
    Button jUpload;
    Button jDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.overridePendingTransition(R.anim.bottom_top,
                R.anim.top_bottm);
        Intent intent = getIntent();
        user = (UserData) intent.getSerializableExtra("USER");
        jUpload= findViewById(R.id.aUploadP);
        jDelete= findViewById(R.id.aDeleteP);

        jUpload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("USER",user);
                        startActivity(intent);
                    }
                }
        );

    }

    public void style()
    {
        FontsSet f=new FontsSet();
        jUpload.setTypeface(f.Book(this));
        jDelete.setTypeface(f.Book(this));
        ScreenSize s=new ScreenSize();
        jUpload.setTextSize((float) (s.size(this)*0.8));
        jDelete.setTextSize((float) (s.size(this)*0.8));
    }
}
