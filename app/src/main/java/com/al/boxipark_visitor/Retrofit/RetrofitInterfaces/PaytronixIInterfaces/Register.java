package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;


import com.al.boxipark_visitor.Register.RegisterWithCard.Models.RegisterResponse.CreatedUserWithCard;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Register {

    @POST("enrollment/register.json")
    Single<CreatedUserWithCard> getPosts(@Body JsonObject json);
}
