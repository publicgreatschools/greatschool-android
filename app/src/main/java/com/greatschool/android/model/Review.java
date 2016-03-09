package com.greatschool.android.model;

public class Review {

    private int mReviewScore;
    private String mReviewInfo;
    private int mReviewImageRes;

    public int getReviewScore() {
        return mReviewScore;
    }

    public String getReviewInfo() {
        return mReviewInfo;
    }

    public int getReviewImageRes() {
        return mReviewImageRes;
    }

    public Review(int reviewScore, String reviewInfo, int reviewImageRes) {
        mReviewScore = reviewScore;
        mReviewInfo = reviewInfo;
        mReviewImageRes = reviewImageRes;
    }
}
