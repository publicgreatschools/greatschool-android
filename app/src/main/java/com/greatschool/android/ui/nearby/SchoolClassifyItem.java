package com.greatschool.android.ui.nearby;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatschool.android.R;

public class SchoolClassifyItem extends RelativeLayout {

    private ImageView mIcon;
    private TextView mCount;
    private TextView mInfo;

    public SchoolClassifyItem(Context context) {
        this(context, null);
    }

    public SchoolClassifyItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SchoolClassifyItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.school_classify_item, this);

        mIcon = (ImageView) view.findViewById(R.id.icon);
        mCount = (TextView) view.findViewById(R.id.count);
        mInfo = (TextView) view.findViewById(R.id.info);


        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SchoolClassifyItem);

            int drawableResource = a.getResourceId(R.styleable.SchoolClassifyItem_classify_icon, 0);
            if (drawableResource != 0) {
                mIcon.setImageResource(drawableResource);
            }

            int count = a.getInteger(R.styleable.SchoolClassifyItem_classify_count, 0);

            if (count == 0) {
                mCount.setVisibility(GONE);
            } else {
                mCount.setVisibility(VISIBLE);
                mCount.setText(String.valueOf(count));
            }

            String info = a.getString(R.styleable.SchoolClassifyItem_classify_info);
            mInfo.setText(info);
            a.recycle();
        }

    }
}
