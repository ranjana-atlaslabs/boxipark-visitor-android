package com.al.boxipark_visitor.Register.RegisterWithoutCard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.BlurBuilder;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterRequest.Data;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterRequest.SetAccountFields;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterRequest.SetUserFields;
import com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterResponse.CreatedUser;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.CreateAndRegister;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import retrofit2.Retrofit;

public class RegisterWithoutCard extends AppCompatActivity {
    CreateAndRegister createAndRegisterApi;
    Button jSign2;
    EditText jName;
    EditText jEmail;
    EditText jMobile;
    EditText jPassword;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ImageView imageViewBackground= findViewById(R.id.aBackReg2);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.backgrn);
        Bitmap blurredBitmap = BlurBuilder.blur( this, icon );

        imageViewBackground.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );
        //Casting
         jSign2= findViewById(R.id.aNewSignup);
         jName= findViewById(R.id.aNewName);
         jEmail= findViewById(R.id.aNewUsername);
         jMobile= findViewById(R.id.aNewMobile);
         jPassword= findViewById(R.id.aNewPassword);

        FontsSet f=new FontsSet();
        jSign2.setTypeface(f.Book(this));
        jName.setTypeface(f.Book(this));
        jEmail.setTypeface(f.Book(this));
        jMobile.setTypeface(f.Book(this));
        jPassword.setTypeface(f.Book(this));

        //Set size
        ScreenSize s=new ScreenSize();
        float size=s.size(this);
        jSign2.setTextSize((float) (size*0.8));
        jName.setTextSize((float) (size*0.6));
        jEmail.setTextSize((float) (size*0.6));
        jMobile.setTextSize((float) (size*0.6));
        jPassword.setTextSize((float) (size*0.6));



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

      runAPI();


    }
public void next()
{
           Intent intent = new Intent(this, MenuActivity.class);
           startActivity (intent);
}
    public void runAPI()
    {
        try {
            API(createProgressDialog(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void storeTokens(String refresh_token,String current_token,String username,String password)
    {
        SharedPreferences.Editor editor = getSharedPreferences("tokens", MODE_PRIVATE).edit();
        editor.putString("refresh_token", refresh_token);
        editor.putString("current_token", current_token);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    public void API(final ProgressDialog progressDialog) throws JSONException {
        Data data=new Data();
        SetAccountFields setAccFields=new SetAccountFields();
        SetUserFields setUserFields = new SetUserFields();

        setUserFields.email = this.jEmail.getText().toString();
        setUserFields.username=this.jEmail.getText().toString();
        setUserFields.firstName=this.jName.getText().toString();
        setUserFields.mobilePhone=this.jMobile.getText().toString();
        setUserFields.password = this.jPassword.getText().toString();

        data.setAccountFields =setAccFields;
        data.setUserFields =setUserFields;

        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        System.out.println(json);
        Retrofit retro= Paytronix.getInstance();
        createAndRegisterApi = retro.create(CreateAndRegister.class);

        createAndRegisterApi.getPosts(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CreatedUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CreatedUser createdUser) {
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

                        //validate all fields before sending the requests
                        if(createdUser.result.equals("cardCreatedSuccess"))
                        {
                            storeTokens(createdUser.oauthTokens.refresh_token,createdUser.oauthTokens.access_token,createdUser.oauthTokens.username,jPassword.getText().toString());
                            next();
                        }
                        else
                        {
                            System.out.println("#########################");
                            System.out.println(createdUser.errorCode);
                            System.out.println(createdUser.errorMessage);
                            System.out.println("#########################");
                            Toast.makeText(getApplicationContext(),createdUser.result,Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });



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
}
