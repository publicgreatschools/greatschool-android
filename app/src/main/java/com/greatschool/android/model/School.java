package com.greatschool.android.model;


import com.google.gson.annotations.SerializedName;

public class School {

    @SerializedName("name")
    private String mName;

    @SerializedName("info")
    private String mInfo;

    @SerializedName("distance")
    private String mDistance;

    @SerializedName("score")
    private int mScore;

    @SerializedName("review")
    private int mReview;

    public String getName() {
        return mName;
    }

    public String getInfo() {
        return mInfo;
    }

    public String getDistance() {
        return mDistance;
    }

    public int getScore() {
        return mScore;
    }

    public int getReview() {
        return mReview;
    }

    private School(Builder builder) {
        this.mName = builder.mName;
        this.mInfo = builder.mInfo;
        this.mDistance = builder.mDistance;
        this.mScore = builder.mScore;
        this.mReview = builder.mReview;
    }

    public static class Builder {

        private final String mName;
        private final String mInfo;
        private String mDistance;
        private int mScore;
        private int mReview;

        public Builder(String name, String info) {
            mName = name;
            mInfo = info;
        }

        public Builder withDistance(String distance) {
            mDistance = distance;
            return this;
        }

        public Builder withScore(int score) {
            mScore = score;
            return this;
        }

        public Builder withReview(int review) {
            mReview = review;
            return this;
        }

        public School build() {
            return new School(this);
        }
    }
}
