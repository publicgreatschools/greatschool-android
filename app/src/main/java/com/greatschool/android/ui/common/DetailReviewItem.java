package com.greatschool.android.ui.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatschool.android.R;
import com.greatschool.android.model.Review;

public class DetailReviewItem extends RelativeLayout {

    private RatingBar mReviewRatingBar;
    private TextView mReviewInfo;
    private ImageView mReviewImage;

    public DetailReviewItem(Context context) {
        this(context, null);
    }

    public DetailReviewItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailReviewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.detail_review_item, this);

        mReviewRatingBar = (RatingBar) findViewById(R.id.review_rating);
        mReviewInfo = (TextView) findViewById(R.id.review_info);
        mReviewImage = (ImageView) findViewById(R.id.review_image);
    }

    public DetailReviewItem create(Review review) {
        mReviewRatingBar.setRating((float) review.getReviewScore() / 2);
        mReviewInfo.setText(review.getReviewInfo());

        int res = review.getReviewImageRes();
        if (res > 0) {
            mReviewImage.setVisibility(VISIBLE);
            mReviewImage.setImageResource(res);
        } else {
            mReviewImage.setVisibility(GONE);
        }

        return this;
    }
}
