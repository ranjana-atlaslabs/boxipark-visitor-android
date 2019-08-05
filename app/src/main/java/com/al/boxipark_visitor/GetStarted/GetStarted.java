package com.al.boxipark_visitor.GetStarted;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelRequest.TokenModelRequest;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelResponse.TokenResponseModel;
import com.al.boxipark_visitor.Login.LoyaltyLogin;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.TokenGenerator;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import android.content.Intent;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.RequestTokens;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import retrofit2.Retrofit;

public class GetStarted extends AppCompatActivity {
    TokenModelRequest data=new TokenModelRequest();
    RequestTokens requestTokensApi;

    TextView jTempGet;
    TextView jTopTempStart;
    Button getStarted;
Context context;
    public static FontsSet f=new FontsSet();
    public static ScreenSize s=new ScreenSize();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        casting();
        style();
        context=this;
        runBack();
        getStarted.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      if(checkStoredData()==1)
                      {
                       runAPI();
                      }
                      else
                      {
                          Intent intent = new Intent(getApplicationContext(), LoyaltyLogin.class);
                          startActivity(intent);
                      }

                    }
                }
        );


    }
    public void style()
    {
        getStarted.setTypeface(f.Book(this));
        jTopTempStart.setTypeface(f.Book(this));
        jTempGet.setTypeface(f.Book(this));

        Float size=s.size(this);

        getStarted.setTextSize((float) (size*0.75));
        jTempGet.setTextSize(size *4);
        jTopTempStart.setTextSize((float) (size*0.8));
    }
    public void casting()
    {
        jTempGet= findViewById(R.id.aTempsStart);
        jTopTempStart= findViewById(R.id.aTopTempStart);
        getStarted= findViewById(R.id.aLogin);
    }
    public void runAPI()
    {
        try {
            API(createProgressDialog(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public int checkStoredData()
    {
        SharedPreferences prefs = getSharedPreferences("tokens", MODE_PRIVATE);

        String username = prefs.getString("username", "no data");
        String password = prefs.getString("password", "");

        if(username.equals("no data")||username.equals(""))
        {
            return 0;
        }
        else
        {
            data.username=username;
            data.password=password;
            return 1;

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
    public void API(final ProgressDialog progressDialog) throws JSONException
    {


        data.grant_type = "password";

        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Retrofit retro= Paytronix.getInstance();
        requestTokensApi = retro.create(RequestTokens.class);

        requestTokensApi.getPosts(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TokenResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TokenResponseModel tokenResponseModel) {
                        if(!tokenResponseModel.result.equals("failed"))
                        {
                          //  Toast.makeText(getApplicationContext(),tokenResponseModel.access_token,Toast.LENGTH_LONG).show();
                            System.out.println(tokenResponseModel.access_token);
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                          //  Toast.makeText(getApplicationContext(),tokenResponseModel.result,Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        if(e.getLocalizedMessage().equals("HTTP 521"))
                        {
                            Toast.makeText(getApplicationContext(),"Server Down",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(), LoyaltyLogin.class);
                            startActivity(intent);
                          //  Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();


                    }
                });



    }

    public void runBack()
    {

        Thread t=new Thread(){


            @Override
            public void run(){

                while(!isInterrupted()){
//1740*1000
                    try {
                        Thread.sleep(1740*1000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                 TokenGenerator tokenGenerator = new TokenGenerator();
                                  tokenGenerator.getTokens(context);

                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();
    }
}
