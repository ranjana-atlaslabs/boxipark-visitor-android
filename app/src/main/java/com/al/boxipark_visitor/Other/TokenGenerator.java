package com.al.boxipark_visitor.Other;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelRequest.TokenModelRequest;
import com.al.boxipark_visitor.Login.LoginModels.TokenModelResponse.TokenResponseModel;
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

import static android.content.Context.MODE_PRIVATE;

public class TokenGenerator {
    RequestTokens requestTokensApi;

    public void storeTokens(String refresh_token, String current_token, String username, Context context)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();

        editor.putString("refresh_token", refresh_token);
        editor.putString("current_token", current_token);
        editor.putString("username", username);

        editor.apply();
    }

    public int getTokens(Context context)
    {
    SharedPreferences prefs = context.getSharedPreferences("tokens", MODE_PRIVATE);
    String restoredText = prefs.getString("text", null);

        String username = prefs.getString("username", "no data");
        String password = prefs.getString("password", "");
        String refresh_token = prefs.getString("refresh_token", "");
        String current_token = prefs.getString("current_token", "");



    if(!username.equals("no data"))
    {
        try {
            API(username,refresh_token,context);
            System.out.println("new token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }
     else
         {
             System.out.println("not generated");
            return 0;
        }
    }

    public void API(String username, final String refresh_toke, final Context context) throws JSONException
    {
        TokenModelRequest data=new TokenModelRequest();


        data.username = username;
        data.refresh_token = refresh_toke;
        data.grant_type = "refresh_token";

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
                            System.out.println("&&&&&&&&&&&&&&&&&&&");
                            System.out.println(tokenResponseModel.access_token);
                            storeTokens(tokenResponseModel.refresh_token,tokenResponseModel.access_token,tokenResponseModel.username,context);

                        }
                        else
                        {

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("#########################");
                        System.out.println(e.toString());
                        if(e.getLocalizedMessage().equals("HTTP 521"))
                        {

                        }
                        else
                        {

                        }
                    }
                });
    }
}
