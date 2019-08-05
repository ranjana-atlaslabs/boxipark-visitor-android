package com.al.boxipark_visitor.Login;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.al.boxipark_visitor.*;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Register.RegisterWithCard.RegisterWithCard;
import com.al.boxipark_visitor.Other.BlurBuilder;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelRequest.TokenModelRequest;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelResponse.TokenResponseModel;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordActivity;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.RequestTokens;
import com.al.boxipark_visitor.VolleyRequests.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import retrofit2.Retrofit;

public class LoyaltyLogin extends AppCompatActivity {
    RequestTokens requestTokensApi;
    SharedPreferences sharedPref;

    EditText jUsername;
    EditText jPassword;
    TextView jSignUp1;
    TextView jForgotpass;
    TextView jSignupText;
    Button login;

    Dialog myDialog;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_login);

        //Blur view for login
        final ImageView imageViewBackground = findViewById(R.id.aLoginBack);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.backgrn);
        Bitmap blurredBitmap = BlurBuilder.blur(this, icon);
        imageViewBackground.setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));

        myDialog = new Dialog(this);
        //Full screen window
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Weather w=new Weather();
        //  final String weather=w.getWeather(this);
        textStyle();
        loginClick();
        signupClick();

        jForgotpass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                        intent.putExtra("from", 1);
                        startActivity(intent);
                    }
                }
        );
        //createProgressDialog(this);
    }

    public void ShowPopup(String body, final String btnBody) {

        myDialog.setContentView(R.layout.popup_alert);
        TextView jPopText = myDialog.findViewById(R.id.aPopupBody);
        Button jPopBtn = myDialog.findViewById(R.id.aPopupBtn);

        jPopBtn.setText(btnBody);

        FontsSet f = new FontsSet();
        Typeface t = f.Book(this);
        jPopText.setTypeface(t);
        jPopBtn.setTypeface(t);


        //screen resizing
        ScreenSize s = new ScreenSize();
        float size = s.size(this);
        jPopText.setTextSize((float) (size * 0.75));
        jPopBtn.setTextSize((float) (size * 0.75));


        jPopText.setText(body);
        jPopBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btnBody.equals("Close")) {
                            Intent intent = new Intent(getApplicationContext(), LoyaltyLogin.class);
                            startActivity(intent);
                        }
                        myDialog.dismiss();
                    }
                }
        );
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public static ProgressDialog createProgressDialog(Context context) {
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

    public void signupClick() {
        jSignUp1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signup1();
                    }
                }
        );
    }

    public void loginClick() {


        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runAPI();

                    }
                }
        );
    }

    public void textStyle() {
        FontsSet f = new FontsSet();
        Typeface t = f.Book(this);
        //casting
        jUsername = findViewById(R.id.aUserName);
        jPassword = findViewById(R.id.aPassword);
        jSignUp1 = findViewById(R.id.aSignUp1);
        jForgotpass = findViewById(R.id.aforgotPass);
        jSignupText = findViewById(R.id.aSignupText);
        login = findViewById(R.id.aLogin);

        login.setTypeface(t);
        jPassword.setTypeface(t);
        jSignUp1.setTypeface(t);
        jForgotpass.setTypeface(t);
        jSignupText.setTypeface(t);
        login.setTypeface(t);


        //screen resizing
        ScreenSize s = new ScreenSize();
        float size = s.size(this);
        jUsername.setTextSize((float) (size * 0.6));
        jPassword.setTextSize((float) (size * 0.6));
        login.setTextSize((float) (size * 0.75));
        jForgotpass.setTextSize((float) (size * 0.6));
        jSignupText.setTextSize((float) (size * 0.6));
        jForgotpass.setTextSize((float) (size * 0.6));
        jSignUp1.setTextSize((float) (size * 0.6));
    }

    public void next() {

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void signup1() {
        Intent intent = new Intent(this, RegisterWithCard.class);
        startActivity(intent);
    }

    public void storeTokens(String refresh_token, String current_token, String username, String password) {
        SharedPreferences.Editor editor = getSharedPreferences("tokens", MODE_PRIVATE).edit();
        editor.putString("refresh_token", refresh_token);
        editor.putString("current_token", current_token);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    public void runAPI() {
        try {
            API(createProgressDialog(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void API(final ProgressDialog progressDialog) throws JSONException {
        TokenModelRequest data = new TokenModelRequest();


        data.username = this.jUsername.getText().toString();
        data.password = this.jPassword.getText().toString();
        data.grant_type = "password";

        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Retrofit retro = Paytronix.getInstance();
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
                        if (!tokenResponseModel.result.equals("failed")) {
                            System.out.println(tokenResponseModel.access_token);
                            storeTokens(tokenResponseModel.refresh_token, tokenResponseModel.access_token, tokenResponseModel.username, jPassword.getText().toString());
                            next();
                        } else {
                            ShowPopup("Incorrect password or e-mail", "Try again");
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################@@@");
                        System.out.println(e.getLocalizedMessage());
                        if (e.getLocalizedMessage().trim().equals("HTTP 521")) {
                            ShowPopup("Server down", "Try again");
                        } else if (e.getLocalizedMessage().trim().equals("HTTP 401")) {
                            ShowPopup("Incorrect password or e-mail", "Try again");
                        } else if (e.getLocalizedMessage().trim().equals("HTTP 403")) {
                            ShowPopup("Incorrect password or e-mail", "Try again");
                        }
                        else if(e.getLocalizedMessage().trim().equals("HTTP 429"))
                        {  ShowPopup("Server could not reached, too many requests", "Try again");}
                             else {
                            ShowPopup("Slow network detetcted", "Try again");
                        }
                        progressDialog.dismiss();


                    }
                });


    }
}


