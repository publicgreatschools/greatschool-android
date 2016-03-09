package com.greatschool.android.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.greatschool.android.R;
import com.greatschool.android.model.Review;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.DetailReviewItem;
import com.greatschool.android.ui.common.TabbedFragment;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends TabbedFragment {

    public static final int INDEX = 2;
    private LinearLayout mReviewLayout;
    private TextView mReviewScore;
    private RatingBar mReviewRatingBar;
    private TextView mReviewCount;
    private TextView mReviewCount1, mReviewCount2, mReviewCount3, mReviewCount4, mReviewCount5;
    private ImageView mReviewImage1, mReviewImage2, mReviewImage3, mReviewImage4, mReviewImage5;

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
        return R.string.review;
    }

    @Override
    public int getIconResId() {
        return 0;
    }

    @Override
    public void registerListenersAndReload() {

        float reviewScore = mSchool.getReviewScore();
        int reviewCount = mSchool.getReview();

        mReviewScore.setText(String.valueOf(reviewScore));
        mReviewRatingBar.setRating(reviewScore);
        mReviewCount.setText(getString(R.string.reviews, reviewCount));

        // 0.04, 0.1 , 0.16, 0. 2, 0. 6
        int reviewScore1 = (int) (reviewCount * 0.6);
        int reviewScore2 = (int) (reviewCount * 0.2);
        int reviewScore3 = (int) (reviewCount * 0.16);
        int reviewScore4 = (int) (reviewCount * 0.1);
        int reviewScore5 = (int) (reviewCount * 0.04);


        int reviewImageWidth1 = getResources().getDimensionPixelOffset(R.dimen.max_review_item_width);
        int reviewImageWidth2 = (int) (reviewImageWidth1 * 0.2 / 0.6);
        int reviewImageWidth3 = (int) (reviewImageWidth1 * 0.16 / 0.6);
        int reviewImageWidth4 = (int) (reviewImageWidth1 * 0.1 / 0.6);
        int reviewImageWidth5 = (int) (reviewImageWidth1 * 0.04 / 0.6);

        mReviewCount1.setText(String.valueOf(reviewScore1));
        mReviewCount2.setText(String.valueOf(reviewScore2));
        mReviewCount3.setText(String.valueOf(reviewScore3));
        mReviewCount4.setText(String.valueOf(reviewScore4));
        mReviewCount5.setText(String.valueOf(reviewScore5));

        mReviewImage1.getLayoutParams().width = reviewImageWidth1;
        mReviewImage2.getLayoutParams().width = reviewImageWidth2;
        mReviewImage3.getLayoutParams().width = reviewImageWidth3;
        mReviewImage4.getLayoutParams().width = reviewImageWidth4;
        mReviewImage5.getLayoutParams().width = reviewImageWidth5;
    }

    @Override
    public void unregisterListenersAndDestroyLoaders() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.review_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mReviewLayout = (LinearLayout) view.findViewById(R.id.review_layout);
        mReviewScore = (TextView) view.findViewById(R.id.review_score);
        mReviewRatingBar = (RatingBar) view.findViewById(R.id.review_rating);
        mReviewCount = (TextView) view.findViewById(R.id.review_count);

        mReviewCount1 = (TextView) view.findViewById(R.id.review_count1);
        mReviewCount2 = (TextView) view.findViewById(R.id.review_count2);
        mReviewCount3 = (TextView) view.findViewById(R.id.review_count3);
        mReviewCount4 = (TextView) view.findViewById(R.id.review_count4);
        mReviewCount5 = (TextView) view.findViewById(R.id.review_count5);

        mReviewImage1 = (ImageView) view.findViewById(R.id.review_image1);
        mReviewImage2 = (ImageView) view.findViewById(R.id.review_image2);
        mReviewImage3 = (ImageView) view.findViewById(R.id.review_image3);
        mReviewImage4 = (ImageView) view.findViewById(R.id.review_image4);
        mReviewImage5 = (ImageView) view.findViewById(R.id.review_image5);


        List<Review> reviewList = simulatedData();

        for (Review review : reviewList) {
            DetailReviewItem detailReviewItem = new DetailReviewItem(getContext()).create(review);
            mReviewLayout.addView(detailReviewItem);
        }
    }


    private List<Review> simulatedData() {
        List<Review> reviewList = new ArrayList<>();
        Review review1 = new Review(7, getString(R.string.review_info1), R.mipmap.review_image1);
        Review review2 = new Review(8, getString(R.string.review_info2), -1);
        Review review3 = new Review(7, getString(R.string.review_info3), R.mipmap.review_image2);
        reviewList.add(review1);
        reviewList.add(review2);
        reviewList.add(review3);
        return reviewList;
    }
}
