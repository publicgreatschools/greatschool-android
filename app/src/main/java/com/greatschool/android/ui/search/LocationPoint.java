package com.greatschool.android.ui.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatschool.android.R;
import com.greatschool.android.model.School;

public class LocationPoint extends RelativeLayout {

    private TextView mSizeView;

    private RelativeLayout mContent;

    private School mSchool;

    public LocationPoint(Context context) {
        this(context, null);
    }

    public LocationPoint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocationPoint(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.location_point, this);

        mSizeView = (TextView) findViewById(R.id.size);
        mContent = (RelativeLayout) findViewById(R.id.content);
    }

    public LocationPoint create(School school) {
        this.mSchool = school;
        mSizeView.setText(String.valueOf(school.getScore()));

        if (school.getScore() <= 6) {
            mContent.setBackgroundResource(R.mipmap.map_location_yellow);
            mSizeView.setTextColor(getResources().getColor(R.color.yellow));
        } else {
            mContent.setBackgroundResource(R.mipmap.map_location_green);
            mSizeView.setTextColor(getResources().getColor(R.color.green));
        }
        return this;
    }

    public void cancelAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out);
        animation.setDuration(300);
        animation.setFillAfter(true);
        mContent.startAnimation(animation);
    }

    public void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        animation.setDuration(300);
        animation.setFillAfter(true);
        mContent.startAnimation(animation);
    }
}
