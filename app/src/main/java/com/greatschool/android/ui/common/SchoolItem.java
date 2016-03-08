package com.greatschool.android.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatschool.android.R;
import com.greatschool.android.model.School;

public class SchoolItem extends RelativeLayout {

    private TextView mSchoolName;
    private TextView mSchoolInfo;
    private TextView mSchoolDistance;

    private TextView mSchoolScore;
    private RatingBar mRatingBar;
    private TextView mReviewCount;

    public SchoolItem(Context context, boolean isMoreItem) {
        this(context, null, isMoreItem);
    }

    public SchoolItem(Context context, AttributeSet attrs, boolean isMoreItem) {
        this(context, attrs, 0, isMoreItem);
    }

    public SchoolItem(Context context, AttributeSet attrs, int defStyleAttr, boolean isMoreItem) {
        super(context, attrs, defStyleAttr);

        if (isMoreItem) {
            View.inflate(context, R.layout.more_school_item, this);
        } else {
            View.inflate(context, R.layout.school_item, this);

            mSchoolName = (TextView) findViewById(R.id.school_name);
            mSchoolInfo = (TextView) findViewById(R.id.school_info);
            mSchoolDistance = (TextView) findViewById(R.id.school_distance);
            mSchoolScore = (TextView) findViewById(R.id.school_score);
            mRatingBar = (RatingBar) findViewById(R.id.school_rating);
            mReviewCount = (TextView) findViewById(R.id.review_count);
        }
    }

    public void create(School school) {
        mSchoolName.setText(school.getName());
        mSchoolInfo.setText(school.getInfo());

        mSchoolDistance.setText(getResources().getString(R.string.school_distance, school.getDistance()));

        mSchoolScore.setText(String.valueOf(school.getScore()));
        mReviewCount.setText(getResources().getString(R.string.reviews, school.getReview()));

        if (school.getScore() <= 6) {
            mSchoolScore.setBackgroundResource(R.mipmap.school_score_yellow);
        } else {
            mSchoolScore.setBackgroundResource(R.mipmap.school_score_green);
        }

        mRatingBar.setRating((float) school.getScore() / 2);
    }
}
