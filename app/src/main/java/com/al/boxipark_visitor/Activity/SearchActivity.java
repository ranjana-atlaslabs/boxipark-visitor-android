package com.al.boxipark_visitor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

public class SearchActivity extends AppCompatActivity {

    EditText jSearchBar;
    TextView jBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.overridePendingTransition(R.anim.enter,
                R.anim.exit);
        jSearchBar= findViewById(R.id.aSearchBar);
        jBack= findViewById(R.id.aNavBack);
        jBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void style()
    {
        FontsSet f=new FontsSet();
        jSearchBar.setTypeface(f.Book(this));
        ScreenSize s=new ScreenSize();
        jSearchBar.setTextSize((float) (s.size(this)*0.8));
    }
}
