package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;

import com.al.boxipark_visitor.ResetPassword.ResetPasswordModel.ResetPasswordData;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ResetPassword {

    @Headers({"Authorization:Basic VmoyaGFKMjM3NjhhWV8tMmhhSjIzRzJKM0dzJi0yQk5EWGFic04xOkpOMlMmMjNoei1Z"})
    @POST("guestmanagement/generateAndSendGuestResetPasswordCode.json")
    Single<ResetPasswordData> getPosts(@Body JsonObject json);

}
