package com.example.boxipark_visitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class MapActivity extends AppCompatActivity {
    WebView mWebView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mWebView = (WebView) findViewById(R.id.aWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        if(getSize()==1)
        {
            mWebView.setInitialScale(110);
        }
        else if (getSize()==2)
        {
            mWebView.setInitialScale(50);
        }
        mWebView.loadUrl("file:///android_asset/index.html");

    }

    public int getSize()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (width > 2000 || height > 2000){
            Toast.makeText(getApplicationContext(), "tablet",Toast.LENGTH_SHORT).show();
            return 1;
        }else{

            Toast.makeText(getApplicationContext(),"phone",Toast.LENGTH_SHORT).show();
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