package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;

import com.al.boxipark_visitor.Login.LoginModels.TokenModelResponse.TokenResponseModel;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestTokens {
    @POST("oauth/requestGuestToken.json")
    Single<TokenResponseModel> getPosts(@Body JsonObject json);
}
