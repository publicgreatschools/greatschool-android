package com.greatschool.android.ui.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.greatschool.android.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    public TabLayout.Tab createTabForFragment(TabbedFragment tabbedFragment, TabLayout tabLayout) {
        Object tag = tabbedFragment.getTitleResId();
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setTag(tag);
        return tab;
    }

    public void addTabForFragment(TabLayout.Tab tabToAdd, TabbedFragment tabbedFragmentToAdd, TabLayout tabLayout) {
        tabLayout.addTab(tabToAdd, tabbedFragmentToAdd.getIndex());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivityAnimation(this);
    }

    public void setBackPressed() {
        super.onBackPressed();
        finishActivityAnimation(this);
    }

    public static void startActivityAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public static void finishActivityAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.enter_back, R.anim.exit_back);
    }
}
