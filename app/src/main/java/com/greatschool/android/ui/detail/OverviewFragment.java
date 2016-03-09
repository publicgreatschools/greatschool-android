package com.greatschool.android.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.greatschool.android.R;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.TabbedFragment;

public class OverviewFragment extends TabbedFragment {

    public static final int INDEX = 0;
    private TextView mScoreText;
    private TextView mReviewText;
    private RatingBar mRatingBar;
    private TextView mLocationText;
    private TextView mReviewCount;
    private School mSchool;

    public void setSchool(School school) {
        mSchool = school;
    }

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.overview;
    }

    @Override
    public int getIconResId() {
        return 0;
    }

    @Override
    public void registerListenersAndReload() {
        mScoreText.setText(String.valueOf(mSchool.getScore()));
        mReviewText.setText(String.valueOf(mSchool.getReview()));
        mLocationText.setText(mSchool.getLocation());
        mReviewText.setText(getString(R.string.user_review_count, mSchool.getReview()));

        float reviewScore = mSchool.getReviewScore();
        mRatingBar.setRating(reviewScore / 2);
        mReviewCount.setText(String.valueOf(reviewScore));
        if (reviewScore <= 6) {
            mReviewCount.setBackgroundResource(R.mipmap.school_score_yellow);
        } else {
            mReviewCount.setBackgroundResource(R.mipmap.school_score_green);
        }
    }

    @Override
    public void unregisterListenersAndDestroyLoaders() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mScoreText = (TextView) view.findViewById(R.id.school_score);
        mReviewText = (TextView) view.findViewById(R.id.school_review);
        mRatingBar = (RatingBar) view.findViewById(R.id.school_rating);
        mLocationText = (TextView) view.findViewById(R.id.location);
        mReviewText = (TextView) view.findViewById(R.id.user_review_count);
        mReviewCount = (TextView) view.findViewById(R.id.school_review);
    }
}
