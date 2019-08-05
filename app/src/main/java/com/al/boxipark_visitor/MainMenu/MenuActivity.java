package com.al.boxipark_visitor.MainMenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.*;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.*;
import com.al.boxipark_visitor.Activity.*;
import com.al.boxipark_visitor.GetStarted.GetStarted;
import com.al.boxipark_visitor.MainMenu.MainMenuTilesCreate.CreateTiles;
import com.al.boxipark_visitor.Other.*;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.UserInformation;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.SingleplatformInterfaces.ISinglePlatform;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.TransactionHistory.HistoryActivity;
import com.al.boxipark_visitor.UserProfile.ProfileActivity;

import static com.al.boxipark_visitor.MainMenu.MainMenuStyles.*;


public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static LinearLayout vScroll;

    //change variable name
    static int menuTileNumber = 0;
    static int size = 0;
    static Context context;
    static ISinglePlatform iSinglePlatform;

    static UserInformation userInfoApi;
    static LinearLayout jHeadMain;
    ImageView jSearch;
    static TextView jRegNumber,jSupport, jCardNumSide, jCardNumHolder, jRegNumberHolder, jSupportHolder, jProfileView, jUserName, jWeatherHead, jTemperature, jLocation, jOffer, jPointsLabel, jPointsHolder;

    static Button jLogout;
    static FrameLayout jMapFrame;

    static Button jMap;
    static Button jMoreInfo;
    static Button jHistory;
    static ImageView jQrView;
    static UserData userDataPublic;

    public static FontsSet fontsSet = new FontsSet();
    public static ScreenSize screenSize = new ScreenSize();
    public static ScreenSize ratio = new ScreenSize();

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //remove default native top bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.overridePendingTransition(R.anim.fadein,
                R.anim.fadeout);

        //draw menu/search bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        context = this;

        //Drawer layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //tool bar action
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        toolbar.setNavigationIcon(R.drawable.menu_icon_n);
        navigationView.setNavigationItemSelectedListener(this);

        vScroll = findViewById(R.id.aVScroll);
        jHeadMain = findViewById(R.id.aMainLayout);
        casting();
        sideMenuStyle();
        toolbarWeatherStyle();
        mapCompStyle();

        searchClick();
        logoutClick();
        profileViewClick();
        moreInfoBtnClick();
        mapBtnClick();
        historyButtonClick();
        API();


    }

    public void logoutClick() {
        jLogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences preferences = getSharedPreferences("tokens", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), GetStarted.class);

                        startActivity(intent);
                    }
                }
        );
    }

    public void searchClick() {
        jSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);

                        startActivity(intent);
                    }
                }
        );
    }

    public void profileViewClick() {
        jProfileView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("USER", userDataPublic);
                        startActivity(intent);
                    }
                }
        );
    }

    public void moreInfoBtnClick() {
        jMoreInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), WeatherModified.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void mapBtnClick() {
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

    public void casting() {
        //Casting
        jCardNumSide = findViewById(R.id.aCardNumSide);
        jSearch = findViewById(R.id.aSearchButton);
        jMapFrame = findViewById(R.id.aMapFrame);
        jMoreInfo = findViewById(R.id.aMoreInfo);
        jRegNumber = findViewById(R.id.aRegNumber);
        jSupport = findViewById(R.id.aSupport);
        jQrView = findViewById(R.id.aQrImage);
        jLogout = findViewById(R.id.aLogout);
        jCardNumHolder = findViewById(R.id.aCardNumHolder);
        jRegNumberHolder = findViewById(R.id.aRegNumberHolder);
        jSupportHolder = findViewById(R.id.aSupportHolder);
        jProfileView = findViewById(R.id.aProfileLink);
        jUserName = findViewById(R.id.aUserName);
        jMap = findViewById(R.id.aMapB);
        jPointsLabel = findViewById(R.id.aLoyaltyPoints);
        jPointsHolder = findViewById(R.id.aLoyaltyPointsHolder);
        jHistory = findViewById(R.id.aHistory);
        jWeatherHead = findViewById(R.id.aWeatherHead);
        jTemperature = findViewById(R.id.aTemperature);
        jLocation = findViewById(R.id.aLocation);
        jOffer = findViewById(R.id.aOffer);
    }

    public void historyButtonClick()
    {
        jHistory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        intent.putExtra("BALANCE", jPointsHolder.getText().toString());
                        intent.putExtra("card",jCardNumHolder.getText().toString());
                        startActivity(intent);
                    }
                }
        );
    }
    public void API() {
        SharedPreferences prefs = getSharedPreferences("tokens", MODE_PRIVATE);
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        MainMenuAPI.initializeAPI(prefs, manager);
    }

    public static void addErrorText(LinearLayout scrollView, String errorText) {
        ScreenSize s = new ScreenSize();
        FontsSet f = new FontsSet();
        TextView textSub = new TextView(context);
        scrollView.addView(textSub);
        textSub.setText(errorText);
        textSub.setTextSize((float) (s.size(context) * 0.9));
        textSub.setTypeface(f.Book(context));
        textSub.setPadding(15, 250, 15, 250);
        textSub.setLineSpacing(0, (float) 1.5);
        textSub.setTextColor(Color.parseColor("#49505D"));
        textSub.setGravity(Gravity.CENTER_HORIZONTAL);
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
        // as you specify a parent activity menuTileNumber AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static LinearLayout addLinear(LinearLayout vScroll) {
        LinearLayout et = new LinearLayout(context);
        et.setOrientation(LinearLayout.HORIZONTAL);
        et.setWeightSum(2);
        et.setDuplicateParentStateEnabled(true);
        et.setLayoutParams(CreateTiles.param);
        et.setRotation(-10);

        vScroll.addView(et);
        return et;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }

    public static float ScreenSize() {
        ScreenSize s = new ScreenSize();
        return s.size(context);
    }

    public static void listners(ImageView v, final RootObject rootObject) {
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(getApplicationContext(),String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
                        next(v.getId(), rootObject);
                        //Toast.makeText(getApplicationContext(),mNavItems.indexOf(0), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public static void next(int id, final RootObject rootObject) {
        //Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ProductMenu.class);
        intent.putExtra("id", String.valueOf(id));
        intent.putExtra("jsonArr", rootObject);

        //  intent.putExtra("sections", Sec);
        context.startActivity(intent);

    }


}
