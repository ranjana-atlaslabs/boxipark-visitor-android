package com.al.boxipark_visitor.ResetPassword;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.*;
import com.al.boxipark_visitor.Login.LoyaltyLogin;
import com.al.boxipark_visitor.Other.BlurBuilder;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordData;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordRequest.ResetRequestModel;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.ResetPassword;
import com.al.boxipark_visitor.UserProfile.ProfileActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText jEmail;
    TextView jTopTextpass, jHeadPass;
    Button jRequestLink;
    ResetPassword resetPassword;
    Dialog myDialog;
    int from;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //Blur view for login
//        final ImageView imageViewBackground= findViewById(R.id.aForgotBack);
//        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
//                R.drawable.backgrn);
//        Bitmap blurredBitmap = BlurBuilder.blur( this, icon );
//        imageViewBackground.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );

        //Full screen window
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        casting();
        styles();

        Intent i = getIntent();
        from = i.getIntExtra("from", 0);
        myDialog = new Dialog(this);

        jRequestLink.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        next();
                    }
                }
        );


    }

    public void next() {
        getLink(createProgressDialog(this));
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
                            if (from == 1) {
                                Intent intent = new Intent(getApplicationContext(), LoyaltyLogin.class);
                                startActivity(intent);
                            } else if (from == 2) {
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                            }
                        }

                        myDialog.dismiss();
                    }
                }
        );
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void casting() {
        jEmail = findViewById(R.id.aValidMail);
        jRequestLink = findViewById(R.id.aRequestLink);
        jTopTextpass = findViewById(R.id.aTopTextPass);
        jHeadPass = findViewById(R.id.aHeadPass);

    }

    public void styles() {
        FontsSet f = new FontsSet();
        Typeface t = f.Book(this);
        jEmail.setTypeface(t);
        jRequestLink.setTypeface(t);
        jTopTextpass.setTypeface(t);
        jHeadPass.setTypeface(f.Medium(this));


        //screen resizing
        ScreenSize s = new ScreenSize();
        float size = s.size(this);
        jEmail.setTextSize((float) (size * 0.6));
        jRequestLink.setTextSize((float) (size * 0.75));
        jTopTextpass.setTextSize((float) (size * 0.75));
        jHeadPass.setTextSize((float) (size * 1.3));

    }

    public ProgressDialog createProgressDialog(Context context) {
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

    public void getLink(final ProgressDialog progressDialog) {
        ResetRequestModel data = new ResetRequestModel();


        data.username = this.jEmail.getText().toString();


        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        Retrofit retro = Paytronix.getInstance();
        resetPassword = retro.create(ResetPassword.class);

        resetPassword.getPosts(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResetPasswordData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResetPasswordData resetPasswordData) {
                        if (!resetPasswordData.result.equals("failure")) {

                            ShowPopup("A reset password link was sent to your e-mail", "Close");


                        } else {
                            ShowPopup(resetPasswordData.errorMessage, "Try Again");

                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        if (e.getLocalizedMessage().equals("HTTP 521")) {
                            ShowPopup("Server down", "Try Again");
                        } else {
                            ShowPopup("Error", "Try Again");
                        }
                        progressDialog.dismiss();


                    }
                });


    }
}
