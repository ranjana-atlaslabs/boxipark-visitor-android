package com.al.boxipark_visitor.MainMenu;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
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
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import com.al.boxipark_visitor.Activity.*;
import com.al.boxipark_visitor.GetStarted.GetStarted;
import com.al.boxipark_visitor.MainMenu.MainMenuTilesCreate.CreateTiles;
import com.al.boxipark_visitor.Other.*;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.UserInformation;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.SingleplatformInterfaces.ISinglePlatform;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.SinglePlatform;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.UserProfile.ProfileActivity;
import com.google.zxing.WriterException;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;


public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout vScroll;
    int in=0;
    Context c;
    ISinglePlatform myApi;
    UserInformation userInfoApi;

    ImageView jSearch;
    TextView jRegNumber;
    TextView jSupport;
    ImageView jQrView;
    TextView jCardNumSide;
    TextView jCardNumHolder;
    TextView jRegNumberHolder;
    TextView jSupportHolder;
    TextView jProfileView;
    TextView jUserName;
    Button jLogout;
    FrameLayout jMapFrame;
    TextView jWeatherHead;
    TextView jTemperature;
    TextView jLocation;
    TextView jOffer;

    Button jMoreInfo;

    UserData userDataPublic;
    public static FontsSet f=new FontsSet();

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.overridePendingTransition(R.anim.fadein,
                R.anim.fadeout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        c=this;
        //Drawer layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        toggle.syncState();
        drawer.addDrawerListener(toggle);
        toolbar.setNavigationIcon(R.drawable.menu_icon_n);
        navigationView.setNavigationItemSelectedListener(this);
        int size=(int)ScreenSize();
        casting();
        jLogout.setTypeface(f.Medium(this));
        jMapFrame.getLayoutParams().height=size*6;
        vScroll = findViewById(R.id.aVScroll);
        ScreenSize s=new ScreenSize();
        jMoreInfo.setTypeface(f.Book(this));
        jMoreInfo.setTextSize((float) (s.size(this)*0.6));
        ScreenSize ratio=new ScreenSize();
        float ration=ratio.ratio(this);
        if(ration>=1.3&&ration<=1.5)
        {
            LinearLayout jHeadMain= findViewById(R.id.aMainLayout);
            jHeadMain.getLayoutParams().height= (int) (ScreenSize()*20);
            jWeatherHead.setTextSize((float) (ScreenSize()*1.4));
            jTemperature.setTextSize(ScreenSize()*3);
            jLocation.setTextSize((float) (ScreenSize()*1.2));
            jOffer.setTextSize((float) (ScreenSize()*0.6));
            jMapFrame.getLayoutParams().height=size*5;
            jMoreInfo.setTextSize((float) (s.size(this)*0.8));

        }
        setFont();
        sliderWeatherConf();
        searchClick();
        logoutClick();
        profileViewClick();
        moreInfoBtnClick();
        mapBtnClick();
        API();

        TokenGenerator t= new TokenGenerator();
        t.getTokens(this);
    }

    public void setFont()
    {
        //Set font style
        jUserName.setTypeface(f.Book(this));
        jCardNumSide.setTypeface(f.Book(this));
        jSupport.setTypeface(f.Book(this));
        jCardNumHolder.setTypeface(f.Book(this));
        jRegNumberHolder.setTypeface(f.Book(this));
        jSupportHolder.setTypeface(f.Book(this));
        jProfileView.setTypeface(f.Book(this));
        jRegNumber.setTypeface(f.Book(this));

        //Set Size
        jCardNumSide.setTextSize((float) (ScreenSize()*0.8));
        jRegNumber.setTextSize((float) (ScreenSize()*0.8));
        jSupport.setTextSize((float) (ScreenSize()*0.8));

        jCardNumHolder.setTextSize((float) (ScreenSize()*0.8));
        jRegNumberHolder.setTextSize((float) (ScreenSize()*0.8));
        jSupportHolder.setTextSize((float) (ScreenSize()*0.8));

        jLogout.setTextSize((float) (ScreenSize()*0.8));
        jLogout.setTypeface(f.Book(this));
        jUserName.setTextSize((ScreenSize()));
        jProfileView.setTextSize((float) (ScreenSize()*0.65));
    }
    public void sliderWeatherConf()
    {
         jWeatherHead= findViewById(R.id.aWeatherHead);
        jWeatherHead.setTextSize((float) (ScreenSize()*0.85));
        jWeatherHead.setTypeface(f.Medium(this));
         jTemperature= findViewById(R.id.aTemperature);
        jTemperature.setTextSize(ScreenSize()*2);
        jTemperature.setTypeface(f.Book(this));
         jLocation= findViewById(R.id.aLocation);
        jLocation.setTextSize((float) (ScreenSize()*0.75));
        jLocation.setTypeface(f.Book(this));
         jOffer= findViewById(R.id.aOffer);
        jOffer.setTextSize((float) (ScreenSize()*0.5));
        jOffer.setTypeface(f.Book(this));
    }
    public void logoutClick()
    {
        jLogout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), GetStarted.class);

                        startActivity(intent);
                    }
                }
        );
    }
    public void searchClick()
    {
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
    public void profileViewClick()
    {
    jProfileView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("USER",userDataPublic);
                    startActivity(intent);
                }
            }
    );
}
    public void moreInfoBtnClick()
    {
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
    public void mapBtnClick()
    {
        Button jMap= findViewById(R.id.aMapB);
        jMap.setTypeface(f.Book(this));
        jMap.setPadding((int) ScreenSize()*2, (int) ((int) ScreenSize()*0.3), (int) ScreenSize()*2, (int) ((int) ScreenSize()*0.3));
        jMap.setTextSize((float) (ScreenSize()*0.7));
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
    public void casting()
    {
        //Casting
        jCardNumSide= findViewById(R.id.aCardNumSide);
        jSearch = findViewById(R.id.aSearchButton);
        jMapFrame= findViewById(R.id.aMapFrame);
        jMoreInfo= findViewById(R.id.aMoreInfo);
        jRegNumber= findViewById(R.id.aRegNumber);
        jSupport= findViewById(R.id.aSupport);
        jQrView = findViewById(R.id.aQrImage);
        jLogout= findViewById(R.id.aLogout);
        jCardNumHolder= findViewById(R.id.aCardNumHolder);
        jRegNumberHolder= findViewById(R.id.aRegNumberHolder);
        jSupportHolder= findViewById(R.id.aSupportHolder);
        jProfileView= findViewById(R.id.aProfileLink);
        jUserName= findViewById(R.id.aUserName);
    }

    public void generateQR()
    {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;

        QRGEncoder qrgEncoder = new QRGEncoder((String) jCardNumHolder.getText(), null, QRGContents.Type.TEXT, smallerDimension);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // Setting Bitmap to ImageView
            jQrView.setImageBitmap(bitmap);
        } catch (WriterException e) {

        }
    }
    public static ProgressDialog createProgressDialog(Context context)
    {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }
    public void API()
    {
        Retrofit retro= SinglePlatform.getInstance();
        myApi=retro.create(ISinglePlatform.class);
        getUserData();
        fetchData(createProgressDialog(this));
    }
    private void fetchData(final ProgressDialog progressDialog)
    {
        SinglePlatform.getInstance().create(ISinglePlatform.class).getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RootObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSuccess(RootObject rootObject) {
                        //
                        for(int i=0;i<5;i++) {
                            LinearLayout et= addLinear(vScroll);

                            addLeft(et,rootObject);
                            addRight(et,rootObject);

                        }
                        progressDialog.dismiss();
                    }


                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        addErrorText(vScroll,"No Data Available");
                    }
                });

    }
    public void getUserData()
    {

        SharedPreferences prefs = getSharedPreferences("tokens", MODE_PRIVATE);

        String current_token = prefs.getString("current_token", "No name defined");//"No name defined" is the default value.
        String username = prefs.getString("username", "No refresh"); //0 is the default value.

        Retrofit paytronix= Paytronix.getInstance();
        userInfoApi=paytronix.create(UserInformation.class);

        //Map data
        Map<String, String> data = new HashMap<>();
        data.put("access_token", current_token);
        data.put("username", username);
        data.put("authentication","oauth");
        data.put("merchantId", String.valueOf(Constants.MERCHANT_ID));

        userInfoApi.getPosts(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UserData userData) {
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

                        //validate all fields before sending the requests
                        if(userData.result.equals("success"))
                        {
                            jUserName.setText(userData.fields.firstName);
                            jCardNumHolder.setText(userData.primaryCardNumbers.get(0));
                            jRegNumberHolder.setText(userData.accountIds.get(0).toString());
                            generateQR();

                            userDataPublic = userData;
                            Toast.makeText(getApplicationContext(),userData.accountIds.get(0).toString(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),userData.result,Toast.LENGTH_LONG).show();
                        }


                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                    }
                });


    }
    public void addErrorText(LinearLayout scrollView,String Text)
    {
        ScreenSize s=new ScreenSize();
        FontsSet f=new FontsSet();
        TextView textSub=new TextView(this);
        scrollView.addView(textSub);
        textSub.setText(Text);
        textSub.setTextSize((float) (s.size(this)*0.9));
        textSub.setTypeface(f.Book(this));
        textSub.setPadding(15,250,15,250);
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
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public LinearLayout addLinear(LinearLayout vScroll)
    {
        LinearLayout et=new LinearLayout(this);
        et.setOrientation(LinearLayout.HORIZONTAL);
        et.setWeightSum(2);
        et.setDuplicateParentStateEnabled(true);
        et.setLayoutParams(CreateTiles.param);
        et.setRotation(-10);

        vScroll.addView(et);
        return et;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        return true;
    }

    public float ScreenSize()
    {
        ScreenSize s=new ScreenSize();
        return s.size(this);
    }

    public void listners(ImageView v, final RootObject rootObject)
    {
        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(getApplicationContext(),String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
                        next(v.getId(),rootObject);
                        //Toast.makeText(getApplicationContext(),mNavItems.indexOf(0), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void next(int id,final RootObject rootObject)
    {
        //Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ProductMenu.class);
            intent.putExtra("id", String.valueOf(id));
            intent.putExtra("jsonArr", rootObject);

          //  intent.putExtra("sections", Sec);
            startActivity(intent);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addLeft(LinearLayout et, RootObject rootObject)
    {

        MenuTiles c=new MenuTiles();
        FrameLayout f=new FrameLayout(this);
        f.setBackground(new ColorDrawable(c.returnCol(in)));
        f.setLayoutParams(CreateTiles.param);
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
        i.setLayoutParams(CreateTiles.param1);


        i.setPadding(0,0,10,0);
        i.setAdjustViewBounds(true);

        if(in==4)
        {
            i.setScaleX((float) 0.5);
            i.setScaleY((float)0.4);
        }
        else if(in==0)
        {
            i.setScaleX((float) 0.65);
            i.setScaleY((float) 0.55);
        }
        else if(in==6)
        {
            i.setScaleX((float) 0.55);
            i.setScaleY((float) 0.45);
        }
        else
        {
            i.setScaleX((float) 0.6);
            i.setScaleY((float) 0.5);
        }


        in++;

        if(in!=9) {
            listners(i,rootObject);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addRight(LinearLayout et,RootObject rootObject)
    {
        MenuTiles c=new MenuTiles();
        FrameLayout f1=new FrameLayout(this);
        f1.setBackground(new ColorDrawable(c.returnCol(in)));
        f1.setLayoutParams(CreateTiles.param);
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

        ii.setLayoutParams(CreateTiles.param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);


        if(in!=1)
        {
            ii.setScaleX((float) 0.55);
            ii.setScaleY((float) 0.45);
        }

        else
        {
            ii.setScaleX((float) 0.5);
            ii.setScaleY((float) 0.4);
        }

        in++;
        listners(ii,rootObject);

    }

    public void addRightBlank(LinearLayout et)
    {
        MenuTiles c=new MenuTiles();
        FrameLayout f1=new FrameLayout(this);
        f1.setForeground(new ColorDrawable(Color.rgb(255,255,255)));
        f1.setLayoutParams(CreateTiles.param);
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

        ii.setLayoutParams(CreateTiles.param1);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ii.setAdjustViewBounds(true);

        ii.setScaleX((float) 0.7);
        ii.setScaleY((float) 0.6);
        in++;

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

}
