package com.al.boxipark_visitor.MainMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import com.al.boxipark_visitor.Other.Constants;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Register.RegisterWithCard.Models.AccountInformation.AccountInfo;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.SinglePlatform;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.AccountInformation;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.UserInformation;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.SingleplatformInterfaces.ISinglePlatform;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

import static com.al.boxipark_visitor.MainMenu.TilesGenerator.addLeft;
import static com.al.boxipark_visitor.MainMenu.TilesGenerator.addRight;

class MainMenuAPI extends MenuActivity {

    static SharedPreferences prefs;
    static WindowManager manager;

    public static void initializeAPI(SharedPreferences Prefs, WindowManager Manager) {
        System.out.println("=======================================");
        prefs = Prefs;
        manager = Manager;
        Retrofit retro= SinglePlatform.getInstance();
        iSinglePlatform =retro.create(ISinglePlatform.class);

        getUserData();
        fetchData(createProgressDialog(context));

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

    public static void generateQR() {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(((String) jCardNumHolder.getText()), BarcodeFormat.PDF_417,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            jQrView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static void getUserData() {
        String current_token = prefs.getString("current_token", "No name defined");
        String username = prefs.getString("username", "No refresh");

        Retrofit paytronix = Paytronix.getInstance();
        userInfoApi = paytronix.create(UserInformation.class);

        //Map data
        Map<String, String> data = new HashMap<>();
        data.put("access_token", current_token);
        data.put("username", username);
        data.put("authentication", "oauth");
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
                        if (userData.result.equals("success")) {
                            jUserName.setText(userData.fields.firstName);
                            jCardNumHolder.setText(userData.primaryCardNumbers.get(0));
                            jRegNumberHolder.setText(userData.accountIds.get(0).toString());
                            generateQR();

                            userDataPublic = userData;
                            getAccountData(createProgressDialog(context),userData.primaryCardNumbers.get(0));

                        } else {
                           // Toast.makeText(context, userData.result, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println("menu error "+e.toString());
//                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private static void fetchData(final ProgressDialog progressDialog) {
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
                        menuTileNumber = 0;
                        for (int i = 0; i < 5; i++) {
                            LinearLayout et = addLinear(vScroll);

                            addLeft(et, rootObject);

                            addRight(et, rootObject);

                            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA " + menuTileNumber);
                        }

                        progressDialog.dismiss();

                    }


                    @Override
                    public void onError(Throwable e) {

                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        addErrorText(vScroll, "No Data Available");
                        progressDialog.dismiss();
                    }
                });

    }

    public static void getAccountData(final ProgressDialog progressDialog,String cardNo)
    {

        AccountInformation accountInfoApi;
        String current_token = prefs.getString("current_token", "No name defined");//"No name defined" is the default value.

        Retrofit paytronix= Paytronix.getInstance();
        accountInfoApi=paytronix.create(AccountInformation.class);
        //Map data
        System.out.println(cardNo+" "+current_token);
        Map<String, String> data = new HashMap<>();
        data.put("access_token", current_token);
        data.put("printedCardNumber", cardNo);
        data.put("authentication","oauth");
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
                        if(accountInfo.result.equals("failed"))
                        {
                         //   Toast.makeText(context,accountInfo.result,Toast.LENGTH_LONG).show();
                        }
                        else if(accountInfo.result.equals("success"))
                        {
                            jPointsHolder.setText(accountInfo.pointBalances.get(0).balance);
                         //   Toast.makeText(context,accountInfo.pointBalances.get(0).balance,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                          //  Toast.makeText(context,accountInfo.result,Toast.LENGTH_LONG).show();
                        }
                     //   Toast.makeText(context,accountInfo.result,Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e.getMessage().equals("HTTP 401 ")) {
                          //  Toast.makeText(context, "Check the card details", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });


    }

}
