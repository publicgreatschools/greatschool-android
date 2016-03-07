package com.greatschool.android.ui.school;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

public class MySchoolFragment extends TabbedFragment {

    public static final int INDEX = 2;

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.my_school;
    }

    @Override
    public int getIconResId() {
        return R.drawable.myschool_icon_selector;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.myschool, container, false);
    }
}
