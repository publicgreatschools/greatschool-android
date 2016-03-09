package com.greatschool.android.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

public class StatsFragment extends TabbedFragment {

    public static final int INDEX = 1;

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.stats;
    }

    @Override
    public int getIconResId() {
        return 0;
    }

    @Override
    public void registerListenersAndReload() {

    }

    @Override
    public void unregisterListenersAndDestroyLoaders() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stats_layout, container, false);
    }
}
