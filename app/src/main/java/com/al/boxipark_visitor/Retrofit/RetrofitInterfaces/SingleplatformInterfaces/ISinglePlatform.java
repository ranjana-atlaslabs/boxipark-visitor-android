package com.al.boxipark_visitor.Retrofit.RetrofitInterfaces.SingleplatformInterfaces;

import com.al.boxipark_visitor.MainMenu.MainMenuModels.Singleplatform.Menus.RootObject;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ISinglePlatform {
    @Headers({"Authorization:SPC:uFklLNNnmjdA3Qe1R6zqKg:v0YAO590OMSlHRS7hoe7jlOGJGU","Content-Type:application/json"})
    @GET("v1/location/boxi-park/all")
    Single<RootObject> getPosts();
}
