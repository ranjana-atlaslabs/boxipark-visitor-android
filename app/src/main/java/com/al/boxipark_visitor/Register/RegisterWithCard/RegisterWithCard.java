package com.al.boxipark_visitor.Register.RegisterWithCard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.al.boxipark_visitor.Other.Constants;
import com.al.boxipark_visitor.Register.RegisterWithCard.Models.AccountInformation.AccountInfo;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.RegisterWithoutCard;
import com.al.boxipark_visitor.Other.BlurBuilder;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.AccountInformation;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

import static com.al.boxipark_visitor.Register.RegisterWithCardFormValidation.*;

public class RegisterWithCard extends AppCompatActivity {
    TextView jSign2;
    TextView jSign2Text;
    EditText jCardNo;
    EditText jRegNo;
    Button jSignup;
    public static Context context;
    AccountInformation accountInfoApi;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        //Full screen window
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ImageView imageViewBackground = findViewById(R.id.aBackRegister);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.backgrn);
        Bitmap blurredBitmap = BlurBuilder.blur(this, icon);

        imageViewBackground.setBackgroundDrawable(new BitmapDrawable(getResources(), blurredBitmap));
        context = this;
        casting();
        style();
        jSignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (runValidate() != 0) {
                            runAPI();
                        }
                    }
                }
        );

        jSign2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      
                            singup2(2);

                    }
                }
        );

    }

    public void casting() {
        //Casting
        jSign2 = findViewById(R.id.aSignUp2);
        jSign2Text = findViewById(R.id.aSignupText);
        jCardNo = findViewById(R.id.aUserNameSign);
        jRegNo = findViewById(R.id.aRegistrationNo);

        jSignup = findViewById(R.id.aSign2B);

    }

    public void style() {
        FontsSet f = new FontsSet();
        jSign2.setTypeface(f.Book(this));
        jCardNo.setTypeface(f.Book(this));
        jRegNo.setTypeface(f.Book(this));

        jSignup.setTypeface(f.Book(this));

        //Set sizes
        ScreenSize s = new ScreenSize();
        float size = s.size(this);
        jSign2Text.setTextSize((float) (size * 0.6));
        jCardNo.setTextSize((float) (size * 0.6));
        jRegNo.setTextSize((float) (size * 0.6));
        jSign2.setTextSize((float) (size * 0.6));
        jSignup.setTextSize((float) (size * 0.8));
    }

    public int runValidate() {
        if (cardNumValidate(jCardNo.getText().toString(), context) == 0) {
            return 0;
        } else if (regNumberValidate(jRegNo.getText().toString(), context) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void singup2(int type) {

        Intent intent = new Intent(this, RegisterWithoutCard.class);
        //type 1= with card
        //type 2= without card
        intent.putExtra("type", type);
        intent.putExtra("cardNo", jCardNo.getText().toString());
        intent.putExtra("regNo", jRegNo.getText().toString());
        startActivity(intent);
    }

    public void runAPI() {
        getAccountData(createProgressDialog(this));
    }

    public void getAccountData(final ProgressDialog progressDialog) {
        String cardNo = jCardNo.getText().toString();
        String regNo = jRegNo.getText().toString();

        if (
                cardNo == null) {
            cardNo = "";
        }
        if (regNo == null) {
            regNo = "";
        }

        Retrofit paytronix = Paytronix.getInstance();
        accountInfoApi = paytronix.create(AccountInformation.class);

        //Map data
        Map<String, String> data = new HashMap<>();
        data.put("registrationCode", regNo);
        data.put("printedCardNumber", cardNo);
        data.put("authentication", "card");
        data.put("merchantId", String.valueOf(Constants.MERCHANT_ID));

        accountInfoApi.getPosts(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AccountInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(AccountInfo accountInfo) {
                        if (accountInfo.result.equals("failed")) {
                            ShowPopup("Check the card details","Try Again",context);
                        } else if (accountInfo.registrationStatus.equals("Registered")) {
                            ShowPopup("Card already registered","Try Again",context);
                        } else if (accountInfo.result.equals("success") && accountInfo.registrationStatus.equals("Unregistered")) {
                            singup2(1);
                        }

                        else
                        {
                            ShowPopup("Card inactive","Try Again",context);
                        }
                        Toast.makeText(getApplicationContext(), accountInfo.result, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().equals("HTTP 401 ")) {
                            ShowPopup("Check the card details","Try Again",context);
                        }
                        progressDialog.dismiss();
                    }
                });


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


}
