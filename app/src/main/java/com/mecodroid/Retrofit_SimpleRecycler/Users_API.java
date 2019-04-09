package com.mecodroid.Retrofit_SimpleRecycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Users_API {

    @GET("search/users")
    Call <GithubUsers> getUsersInfo(@Query("q") String name);
}
