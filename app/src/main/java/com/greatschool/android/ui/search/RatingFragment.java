package com.greatschool.android.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

public class RatingFragment extends TabbedFragment {
    public static final int INDEX = 2;

    @Override
    public int getIndex() {
        return INDEX;
    }
    @Override
    public int getTitleResId() {
        return R.string.ratings;
    }

    @Override
    public int getIconResId() {
        return 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating, container, false);
    }
}
