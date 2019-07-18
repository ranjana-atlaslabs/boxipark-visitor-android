package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;


import com.al.boxipark_visitor.Register.RegisterWithoutCard.Models.CreateAndRegisterResponse.CreatedUser;

import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;



public interface CreateAndRegister {

    @POST("enrollment/createAndRegister.json")
    Single<CreatedUser> getPosts(@Body JsonObject json);
}
