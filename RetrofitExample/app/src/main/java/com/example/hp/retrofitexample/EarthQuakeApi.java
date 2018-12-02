package com.example.hp.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthQuakeApi {
    @GET("/fdsnws/event/1/query?")
    Call<List<EarthQuakeAPIRecord>> getEarthquakes(
    //Call<List<Post>> getPosts(
            @Query("format") String format,
            @Query("minmagnitude") String minMagnitude,
            @Query("starttime") String startTime,
            @Query("orderby") String orderBy);

    Call<EarthQuakeAPIResponse> getEarthquakes();
}
