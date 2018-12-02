package com.example.hp.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Post {

    private double mag;

    private String place;

    private long time;

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }
}
