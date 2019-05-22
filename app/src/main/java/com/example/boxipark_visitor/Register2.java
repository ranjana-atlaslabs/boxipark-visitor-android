package com.example.boxipark_visitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Register2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button jSign2=(Button)findViewById(R.id.aSignUp2);
        jSign2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
toMain();
                    }
                }
        );
    }

    public void toMain()
    {
        Intent intent = new Intent(this, MenuActivity2.class);
        startActivity ( intent );


    }
}
