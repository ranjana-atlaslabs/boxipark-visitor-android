package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;

import com.al.boxipark_visitor.MainMenu.MainMenuModels.Paytronix.GetUserInformation.UserData;
import com.al.boxipark_visitor.Register.RegisterWithCard.Models.AccountInformation.AccountInfo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface AccountInformation {

    @Headers({"Authorization:Basic VmoyaGFKMjM3NjhhWV8tMmhhSjIzRzJKM0dzJi0yQk5EWGFic04xOkpOMlMmMjNoei1Z"})
    @GET("guest/accountInformation.json")
    Single<AccountInfo> getPosts(@QueryMap Map<String, String> options);
}
