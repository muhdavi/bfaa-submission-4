package com.onesoul.moviecataloguels.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<String> getDiscoverMovie(@Query("api_key") String API_KEY,
                                  @Query("language") String language);

    @GET("discover/tv")
    Call<String> getDiscoverTv(@Query("api_key") String API_KEY,
                               @Query("language") String language);
}
