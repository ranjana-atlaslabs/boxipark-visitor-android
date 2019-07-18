package com.al.boxipark_visitor.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.*;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;

@SuppressLint("SetJavaScriptEnabled")
public class MapActivity extends AppCompatActivity {
    WebView mWebView;
    private ProgressBar progressBar;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Cast webview
        mWebView = findViewById(R.id.aWebView);
        //Set options
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        mWebView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {

               // Toast.makeText(getApplicationContext(), "tablet",Toast.LENGTH_SHORT).show();
            }
        });

        //Cast back Button
        TextView jmap= findViewById(R.id.aMapBottom);
        ScreenSize s=new ScreenSize();
        jmap.setTextSize(s.size(this));

        if(getSize()==1)
        {
            mWebView.setInitialScale(150);
        }
        else if (getSize()==2)
        {
            mWebView.setInitialScale(100);
        }
        mWebView.loadUrl("file:///android_asset/index.html");
        ImageView jMap= findViewById(R.id.aMenuBackM);
        jMap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }



    public int getSize()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (width > 2000 || height > 2000){
           // Toast.makeText(getApplicationContext(), "tablet",Toast.LENGTH_SHORT).show();
            return 1;
        }else{

           // Toast.makeText(getApplicationContext(),"phone",Toast.LENGTH_SHORT).show();
            return 2;
        }
    }

    public class WebAppInterface {

        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void nextScreen(String pro_cat_id) {
            Toast.makeText(getApplicationContext(), String.valueOf(pro_cat_id),Toast.LENGTH_SHORT).show();

        }
    }
}