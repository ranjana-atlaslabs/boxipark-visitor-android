package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.PaytronixIInterfaces;

import com.al.boxipark_visitor.UserProfile.profileModels.ProfileEditResponse.EditProfileResponse;
import com.google.gson.JsonObject;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateUserData {

    @Headers({"Authorization:Basic VmoyaGFKMjM3NjhhWV8tMmhhSjIzRzJKM0dzJi0yQk5EWGFic04xOkpOMlMmMjNoei1Z"})
    @POST("enrollment/editAccount.json")
    Single<EditProfileResponse> getPosts(@Body JsonObject json);
}
