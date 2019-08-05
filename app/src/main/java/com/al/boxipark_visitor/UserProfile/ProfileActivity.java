package com.al.boxipark_visitor.UserProfile;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.al.boxipark_visitor.Activity.UploadPhoto;
import com.al.boxipark_visitor.Login.LoyaltyLogin;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordActivity;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordData;
import com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordRequest.ResetRequestModel;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.ResetPassword;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.UpdateUserData;
import com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditRequest.DataEdit;
import com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditRequest.SetAccountFieldsEdit;
import com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditRequest.SetUserFieldsEdit;
import com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditResponse.EditProfileResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {
    TextView jEdit, jPrivate, jFullname, jEmail, jPhone, jReset, jNew, jConfirm;
    EditText jNameText, jEmailText, jPhoneText, jCurrentP, jNewP, jConfirmP;
    Button jSave, jcancel;
    Button jCurrent;
    Dialog myDialog;
    public UserData user;
    UpdateUserData updateUserDataApi;
    ResetPassword resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        casting();
        setTextSize();
        setTextType();
        Intent intent = getIntent();

        editBtnClick();

        cancelBtnClick();
        saveBtnClick();
        myDialog = new Dialog(this);
        user = (UserData) intent.getSerializableExtra("USER");
        jNameText.setText(user.fields.firstName);
        jEmailText.setText(user.fields.email);
        jPhoneText.setText("+" + user.fields.mobilePhone);

//        jReset.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
////                        intent.putExtra("from", 2);
////                        startActivity(intent);
//                        getLink();
//                    }
//                }
//        );

        jCurrent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getLink();
                    }
                }
        );


    }


    public void setTextType() {
        FontsSet f = new FontsSet();
        Typeface t = f.Book(this);
        //Casting
        setTextSize();
        jEdit.setTypeface(t);
        jPrivate.setTypeface(t);
        jFullname.setTypeface(t);
        jEmail.setTypeface(t);
        jPhone.setTypeface(t);
        jReset.setTypeface(t);
        jCurrent.setTypeface(t);
        jConfirm.setTypeface(t);
        jNameText.setTypeface(t);
        jEmailText.setTypeface(t);
        jPhoneText.setTypeface(t);
        jCurrentP.setTypeface(t);
        jNew.setTypeface(t);
        jNewP.setTypeface(t);
        jConfirmP.setTypeface(t);
        jSave.setTypeface(t);
        jcancel.setTypeface(t);
    }

    public void setTextSize() {
        ScreenSize s = new ScreenSize();
        float sizei = (s.size(this));
        jEdit.setTextSize((float) (sizei * 0.6));
        jPrivate.setTextSize((float) (sizei * 0.7));
        jFullname.setTextSize((float) (sizei * 0.7));
        jEmail.setTextSize((float) (sizei * 0.7));
        jPhone.setTextSize((float) (sizei * 0.7));
        jReset.setTextSize((float) (sizei * 0.7));
        jCurrent.setTextSize((float) (sizei * 0.7));
        jNew.setTextSize((float) (sizei * 0.7));
        jConfirm.setTextSize((float) (sizei * 0.7));


        jNameText.setTextSize((float) (sizei * 0.65));
        jEmailText.setTextSize((float) (sizei * 0.65));
        jPhoneText.setTextSize((float) (sizei * 0.65));
        jCurrentP.setTextSize((float) (sizei * 0.65));
        jNewP.setTextSize((float) (sizei * 0.65));
        jConfirmP.setTextSize((float) (sizei * 0.65));

        jSave.setTextSize((float) (s.size(this) * 0.8));
        jcancel.setTextSize((float) (s.size(this) * 0.8));
    }

    public void editBtnClick() {
        jEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), UploadPhoto.class);
                        intent.putExtra("USER", user);
                        startActivity(intent);
                    }
                }
        );
    }

    public void cancelBtnClick() {
        jcancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void saveBtnClick() {
        jSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!user.fields.username.equals(jNameText) || !user.fields.mobilePhone.equals(jPhoneText)) {
                            String name = jNameText.getText().toString().trim();
                            String phone = jPhoneText.getText().toString().trim();

                            if (name.isEmpty() || name.length() == 0 || name.equals("") || name == null || phone.isEmpty() || phone.length() == 0 || phone.equals("") || phone == null)
                            {
                                //Toast.makeText(getApplicationContext(), "Blanks detetcted", Toast.LENGTH_LONG).show();
                                ShowPopup("Blanks detected","try again");
                            }
                            else
                            {
                                getTokens();


                            }

                        }
                        else {
                           ShowPopup("Fields were not modified","Close");
                        }

                    }
                }
        );
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
                        myDialog.dismiss();
                    }
                }
        );
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
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

    public int getTokens() {
        SharedPreferences prefs = getSharedPreferences("tokens", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);

        String current_token = prefs.getString("current_token", "");

        if (!current_token.equals("")) {


            try {
                updateData(createProgressDialog(this), current_token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 1;
        } else {
            return 0;
        }
    }


    @SuppressLint("WrongViewCast")
    public void casting() {
        jEdit = findViewById(R.id.aEditPhoto);
        jPrivate = findViewById(R.id.aPInfo);
        jFullname = findViewById(R.id.aFullName);
        jEmail = findViewById(R.id.aEmail);
        jPhone = findViewById(R.id.aPhone);
        jReset = findViewById(R.id.aReset);
        jCurrent = findViewById(R.id.aCurrentP);
        jNew = findViewById(R.id.aNewP);
        jConfirm = findViewById(R.id.aConfirmP);

        jNameText = findViewById(R.id.aFullNameText);
        jEmailText = findViewById(R.id.aEmailText);
        jPhoneText = findViewById(R.id.aPhoneText);
        jCurrentP = findViewById(R.id.aCurrentPText);
        jNewP = findViewById(R.id.aNewPText);
        jConfirmP = findViewById(R.id.aConfirmPText);

        jSave = findViewById(R.id.aSave);
        jcancel = findViewById(R.id.aCancel);

    }

    public void updateData(final ProgressDialog progressDialog, String current) throws JSONException {
        DataEdit data = new DataEdit();
        SetAccountFieldsEdit setAccFields = new SetAccountFieldsEdit();
        SetUserFieldsEdit setUserFields = new SetUserFieldsEdit();

        setUserFields.firstName = this.jNameText.getText().toString();
        setUserFields.mobilePhone = this.jPhoneText.getText().toString();
        data.access_token = current;
        data.printedCardNumber = user.primaryCardNumbers.get(0);
        data.setAccountFields = setAccFields;
        data.setUserFields = setUserFields;

        Gson gson = new Gson();
        String json = gson.toJson(data);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        System.out.println(json);
        Retrofit retro = Paytronix.getInstance();
        updateUserDataApi = retro.create(UpdateUserData.class);

        updateUserDataApi.getPosts(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<EditProfileResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(EditProfileResponse editProfileResponse) {


                            System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

                            //validate all fields before sending the requests
                            if (editProfileResponse.result.equals("success")) {
                                // storeTokens(createdUser.oauthTokens.refresh_token,createdUser.oauthTokens.access_token,createdUser.oauthTokens.username,jPassword.getText().toString());
                                ShowPopup("Update successful","Close");
                            } else {
                                System.out.println("#########################");
                                System.out.println(editProfileResponse.errorCode);
                                System.out.println(editProfileResponse.errorMessage);
                                System.out.println("#########################");
                              //  Toast.makeText(getApplicationContext(), editProfileResponse.result, Toast.LENGTH_LONG).show();
                            }


                        progressDialog.dismiss();

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                       // Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });


    }

    public void getLink() {
        ResetRequestModel data = new ResetRequestModel();


        data.username = this.jEmailText.getText().toString();


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
                      //  progressDialog.dismiss();
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
                     //   progressDialog.dismiss();


                    }
                });


    }
}
