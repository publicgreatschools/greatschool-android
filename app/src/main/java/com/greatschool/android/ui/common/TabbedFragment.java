package com.greatschool.android.ui.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public abstract class TabbedFragment extends Fragment {


    abstract public int getIndex();

    abstract public @StringRes int getTitleResId();

    abstract public @DrawableRes int getIconResId();

    abstract public void registerListenersAndReload();

    abstract public void unregisterListenersAndDestroyLoaders();

    @Override
    public void onStart() {
        super.onStart();
        registerListenersAndReload();
    }

    @Override
    public void onStop() {

        unregisterListenersAndDestroyLoaders();
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            registerListenersAndReload();
        } else {
            unregisterListenersAndDestroyLoaders();
        }
    }
}
