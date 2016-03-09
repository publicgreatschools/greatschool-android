package com.greatschool.android.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class School implements Serializable {

    @SerializedName("name")
    private String mName;

    @SerializedName("info")
    private String mInfo;

    @SerializedName("distance")
    private float mDistance;

    @SerializedName("score")
    private int mScore;

    @SerializedName("review")
    private int mReview;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("reviewScore")
    private float mReviewScore;

    public String getName() {
        return mName;
    }

    public String getInfo() {
        return mInfo;
    }

    public float getDistance() {
        return mDistance;
    }

    public int getScore() {
        return mScore;
    }

    public int getReview() {
        return mReview;
    }

    public String getLocation() {
        return mLocation;
    }

    public float getReviewScore() {
        return mReviewScore;
    }

    public School(String name, String info, float distance, int score, int review, String location, float reviewScore) {
        mName = name;
        mInfo = info;
        mDistance = distance;
        mScore = score;
        mReview = review;
        mLocation = location;
        mReviewScore = reviewScore;
    }
}
