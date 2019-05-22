package com.example.boxipark_visitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoyaltyLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_login);
        EditText jUsername=(EditText)findViewById(R.id.aUserName);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView jSignUp1=(TextView)findViewById(R.id.aSignUp1);

        jSignUp1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signup1();
                    }
                }
        );
        Button login=(Button)findViewById(R.id.aSignUp2);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      next();
                    }
                }
        );
    }

    public void next()
    {
        Intent intent = new Intent(this, MenuActivity2.class);
        startActivity ( intent );
    }
    public void signup1()
    {
        Intent intent = new Intent(this, Register1.class);
        startActivity ( intent );
    }
}
