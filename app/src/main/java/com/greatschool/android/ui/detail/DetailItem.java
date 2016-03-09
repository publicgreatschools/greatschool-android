package com.greatschool.android.ui.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.greatschool.android.R;

public class DetailItem extends RelativeLayout {


    public DetailItem(Context context) {
        this(context, null);
    }

    public DetailItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View.inflate(context, R.layout.detail_item, this);
    }



}
