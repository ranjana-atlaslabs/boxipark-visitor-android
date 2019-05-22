package com.example.boxipark_visitor;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.*;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout vScroll;
    ImageView home_map;
    ScrollView jScroll;
    int heightm;
    ImageView img[];
    int in=0;
Button jMoreInfo;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.toolbar);
       // toolbar.setLogo(R.drawable.logo);

        setSupportActionBar(toolbar);
        setTitle("");

       // heightm=home_map.getBaseline();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        vScroll = (LinearLayout) findViewById(R.id.aVScroll);
        //addImageMap();
        for(int i=0;i<5;i++) {

            addItem();
        }

        /* jScroll=(ScrollView)findViewById(R.id.aScroll);
        jScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = jScroll.getScrollY(); //for verticalScrollView
                if (scrollY == 0) {

                  //  animate(heightm);
                   // animate1(jScroll,200f);
                }
                else {
                 //   animate(-heightm);
                    //animate1(jScroll,-200f);
                  //  Toast.makeText(getApplicationContext(),String.valueOf(heightm), Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        jMoreInfo=(Button)findViewById(R.id.aMoreInfo);
        jMoreInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button jMap=(Button)findViewById(R.id.aMapB);
        jMap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void animate(float value)
    {
        ObjectAnimator animUp=ObjectAnimator.ofFloat(home_map,"y",value);
        animUp.setDuration(1000);
        AnimatorSet animSet=new AnimatorSet();
        animSet.playTogether(animUp);
        animSet.start();
    }
    /*public void animate1(View view,float value)
    {
        ObjectAnimator animUp=ObjectAnimator.ofFloat(jScroll,"y",value);
        animUp.setDuration(1000);
        AnimatorSet animSet=new AnimatorSet();
        animSet.playTogether(animUp);
        animSet.start();
    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       /* int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }
    public void remove()
    {
        if(vScroll.getChildCount()!=0)
        {
            vScroll.removeViewAt(vScroll.getChildCount()-1);
        }
    }

    public void addImageMap()
    {
        ImageView i1=new ImageView(this);
        vScroll.addView(i1);
        i1.setImageResource(R.drawable.home_map);
        i1.bringToFront();
        //i1.setId(in);
        i1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //i.setLayoutParams(param1);
       // i.setPadding(0,0,10,0);
        i1.setAdjustViewBounds(true);
        //i.setScaleX((float) 0.8);
        //i.setScaleY((float) 0.6);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createBlank()
    {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        LinearLayout et1=new LinearLayout(this);
        et1.setOrientation(LinearLayout.HORIZONTAL);
        et1.setWeightSum(2);
        et1.setDuplicateParentStateEnabled(true);

        et1.setRotation(-10);
        vScroll.addView(et1);

        ImageView Blank1=new ImageView(this);
        et1.addView(Blank1);
        Blank1.setId(in);
        Blank1.setImageResource(R.drawable.logo);
        Blank1.setRotation(10);
        Blank1.setScaleY(2);
        Blank1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        Blank1.setLayoutParams(param);

        ImageView Blank2=new ImageView(this);
        et1.addView(Blank2);
        Blank2.setId(in);
        Blank2.setImageResource(R.drawable.logo);
        Blank2.setRotation(10);
        Blank2.setScaleY(2);
        Blank2.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        Blank2.setLayoutParams(param);




    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addItem()
    {
        MenuTiles c=new MenuTiles();
       // remove();
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        FrameLayout.LayoutParams param1 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );

        LinearLayout et=new LinearLayout(this);
        et.setOrientation(LinearLayout.HORIZONTAL);
        et.setWeightSum(2);
        et.setDuplicateParentStateEnabled(true);
        et.setLayoutParams(param);
        et.setRotation(-10);
        // et.setScaleX((float) 1.1);
        vScroll.addView(et);

        FrameLayout f=new FrameLayout(this);
        f.setBackground(new ColorDrawable(c.returnCol(in)));
        f.setLayoutParams(param);
        f.setRotation(10);
        f.setScaleY((float) 1.3);
if(in==0)
{
    f.setPadding(55,60,0,0);
}
else if(in==8)
{
    f.setPadding(55,0,0,60);
}
else
{
    f.setPadding(55,0,0,0);
}

        et.addView(f);

        ImageView i=new ImageView(this);
        f.addView(i);
        i.setImageResource(c.returnImg(in));
        i.setId(in);
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        i.setLayoutParams(param1);


        i.setPadding(0,0,10,0);
        i.setAdjustViewBounds(true);
        i.setScaleX((float) 0.8);
        i.setScaleY((float) 0.6);

        in++;


        FrameLayout f1=new FrameLayout(this);
        f1.setBackground(new ColorDrawable(c.returnCol(in)));
        f1.setLayoutParams(param);
        f1.setRotation(10);
        f1.setScaleY((float) 1.3);
        if(in==1)
        {
            f1.setPadding(0,60,55,0);
        }
        else if(in==9)
        {
            f1.setPadding(0,0,55,60);
        }
        else
        {
            f1.setPadding(0,0,55,0);
        }

        et.addView(f1);





        ImageView ii=new ImageView(this);
        f1.addView(ii);
        ii.setId(in);
        ii.setImageResource(c.returnImg(in));

        ii.setLayoutParams(param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);

        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        in++;
        //createBlank();








        listners(i);
        listners(ii);



    }
    public void listners(ImageView v)
    {
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getApplicationContext(),String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
                        next(v.getId());
                    }
                }
        );
    }

    public void next(int id)
    {
        Intent intent;
        if(id%2==1)
        {
            intent = new Intent(this, ProductMenu.class);
            startActivity(intent);
          //  getWeather();

        }
        else
        {
            intent = new Intent(this, MapActivity.class);
            startActivity(intent);
         //  getWeather();

        }

       /* startActivity ( intent );
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);*/
    }
    public void getWeather() {
        final String[] res = new String[1];
        RequestQueue ExampleRequestQueue = (RequestQueue) Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=-81.278239&lon=28.373073&appid=798133ac41c425102ded214330e37a67";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen.
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
               // res[0] = response;
                try {
                    JSONObject reader = new JSONObject(response);
                    JSONObject sys  = reader.getJSONObject("weather");
                    Toast.makeText(getApplicationContext(),sys.getString("main"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                res[0] = String.valueOf(error);
            }
        });

        ExampleRequestQueue.add(ExampleStringRequest);


      //  return res[0];
    }
    public void deleteItem()
    {
        if(vScroll.getChildCount()!=0)
        {
            vScroll.removeViewAt(vScroll.getChildCount()-1);



        }
        if(vScroll.getChildCount()!=1&&in>0)
        {
            in=in-2;


        }

    }




}
