package com.greatschool.android.ui.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public abstract class TabbedFragment extends Fragment {

    abstract public int getIndex();

    abstract public @StringRes int getTitleResId();

    abstract public @DrawableRes int getIconResId();
}
