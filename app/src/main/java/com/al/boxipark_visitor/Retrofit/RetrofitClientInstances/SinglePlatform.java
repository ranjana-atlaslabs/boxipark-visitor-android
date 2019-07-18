package com.al.boxipark_visitor.Retrofit.RetrofitClientInstances;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SinglePlatform {
    private static Retrofit ourInstance ;

    public static Retrofit getInstance() {
          if(ourInstance==null)
              ourInstance=new Retrofit.Builder()
                      .baseUrl("https://customer-api.singleplatform.com/")
                      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
        return ourInstance;
    }

    private SinglePlatform() {
    }
}
