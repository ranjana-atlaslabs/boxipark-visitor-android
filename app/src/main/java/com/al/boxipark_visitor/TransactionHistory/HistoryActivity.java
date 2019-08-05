package com.al.boxipark_visitor.TransactionHistory;

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
import android.widget.*;
import com.al.boxipark_visitor.Adapters.CustomHistoryAdapter;
import com.al.boxipark_visitor.Login.LoyaltyLogin;
import com.al.boxipark_visitor.MainMenu.MenuActivity;
import com.al.boxipark_visitor.Other.Constants;
import com.al.boxipark_visitor.Other.FontsSet;
import com.al.boxipark_visitor.Other.ScreenSize;
import com.al.boxipark_visitor.R;
import com.al.boxipark_visitor.Retrofit.RetrofitClientInstances.Paytronix;
import com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces.TransactionInformation;
import com.al.boxipark_visitor.TransactionHistory.HistoryModels.HistoryDataBase;
import com.al.boxipark_visitor.TransactionHistory.HistoryModels.HistoryRequest.TransactionRequest;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.al.boxipark_visitor.MainMenu.MenuActivity.fontsSet;


public class HistoryActivity extends AppCompatActivity {

    TextView jTopText, jTopPoints;
    ImageView backBtn;
    public String cardNum;
    ListView jTransactionList;
    TransactionInformation transactionInformation;
    public static ScreenSize screenSize = new ScreenSize();
public TransactionRequest transactionRequest;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.overridePendingTransition(R.anim.fadein,
                R.anim.fadeout);
        Intent i = getIntent();

        casting();
        style();
        myDialog = new Dialog(this);
        jTopPoints.setText(i.getStringExtra("BALANCE"));
        cardNum = i.getStringExtra("card");
        getTransactiondata(createProgressDialog(this));
        backBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                        startActivity(intent);
                    }
                }
        );
    }

    public void casting() {
        jTopText = findViewById(R.id.aTopText);
        jTopPoints = findViewById(R.id.aTopPoints);
        backBtn = findViewById(R.id.aBackBtn);
        jTransactionList = findViewById(R.id.aTransactionList);
    }

    public void style() {
        float ratio = screenSize.size(this);

        jTopPoints.setTextSize((float) (ratio) * 4);
        jTopPoints.setTypeface(fontsSet.Bold(this));

        jTopText.setTextSize((float) (ratio));
        jTopText.setTypeface(fontsSet.Book(this));

    }

    //            HistoryDataBase d=new HistoryDataBase("Barnona","-5.0",transactionRequest.transactions.get(i).transactionId,format.format(date1));
//            HistoryDataBase d2=new HistoryDataBase("Canonita","+2.4",transactionRequest.transactions.get(i).transactionId,format.format(date1));
//            hdb.add(d);
//            hdb.add(d2);
    public void setSampleData(){

     ArrayList<HistoryDataBase> hdb = new ArrayList<>();

        for (int i = 0; i < transactionRequest.transactions.size(); i++) {
            SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy 'at' HH:mm a.");

            Date date1= null;
            try {
                date1 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(transactionRequest.transactions.get(i).datetime);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            for (int ii = 0; ii < transactionRequest.transactions.get(i).details.size(); ii++) {
                    if(transactionRequest.transactions.get(i).details.size()>0) {
                    if (!transactionRequest.transactions.get(i).details.get(ii).redeemed.equals("0")) {
                        HistoryDataBase d1 = new HistoryDataBase(transactionRequest.transactions.get(i).storeName, transactionRequest.transactions.get(i).details.get(ii).redeemed, transactionRequest.transactions.get(i).transactionId, format.format(date1));
                        hdb.add(d1);
                    } else {
                        HistoryDataBase d1 = new HistoryDataBase(transactionRequest.transactions.get(i).storeName,"+"+transactionRequest.transactions.get(i).details.get(ii).accrued, transactionRequest.transactions.get(i).transactionId, format.format(date1));
                        hdb.add(d1);
                    }
                    }


                }

        }
//        Toast.makeText(getApplicationContext(), transactionRequest.transactions.size(), Toast.LENGTH_LONG).show();t
        if(hdb.size()==0)
        {
ShowPopup("No transactions Found","Back");
        }
        else
        {
            CustomHistoryAdapter adapter = new CustomHistoryAdapter(this, R.layout.adapter_history, hdb);
            jTransactionList.setAdapter(adapter);
        }

    }

    public void getTransactiondata(final ProgressDialog progressDialog) {

        SharedPreferences prefs = this.getSharedPreferences("tokens", MODE_PRIVATE);

        String current_token = prefs.getString("current_token", "");


        Retrofit paytronix = Paytronix.getInstance();
        transactionInformation = paytronix.create(TransactionInformation.class);

        //Map data
        Map<String, String> data = new HashMap<>();
        data.put("access_token", current_token);
        data.put("printedCardNumber", cardNum);
        data.put("authentication", "oauth");
        data.put("merchantId", String.valueOf(Constants.MERCHANT_ID));

        transactionInformation.getPosts(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TransactionRequest>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TransactionRequest ttransactionRequest) {
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");

                        //validate all fields before sending the requests
                        if (ttransactionRequest.result.equals("success")) {
                            transactionRequest=ttransactionRequest;
                            setSampleData();

                        } else {
                            Toast.makeText(getApplicationContext(), ttransactionRequest.result, Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        ShowPopup("Error loading transactions","Back");
                        //  Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
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

                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);

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
}
