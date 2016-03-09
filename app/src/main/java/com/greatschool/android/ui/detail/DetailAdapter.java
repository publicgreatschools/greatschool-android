package com.greatschool.android.ui.detail;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

import java.util.List;

public class DetailAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SearchAdapter";
    private final List<TabbedFragment> mFragments;
    private final FragmentActivity mActivity;

    public DetailAdapter(FragmentActivity activity) {
        this(activity, ImmutableList.<TabbedFragment>builder()
                .add(new OverviewFragment())
                .add(new StatsFragment())
                .add(new ReviewFragment())
                .build());
    }

    private DetailAdapter(FragmentActivity activity, List<TabbedFragment> fragments) {
        super(activity.getSupportFragmentManager());
        mActivity = activity;
        mFragments = fragments;
    }

    @Override
    public TabbedFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final int titleResId;

        switch (position) {
            case OverviewFragment.INDEX:
                titleResId = getOverviewFragment().getTitleResId();
                break;
            case StatsFragment.INDEX:
                titleResId = getStatsFragment().getTitleResId();
                break;
            case ReviewFragment.INDEX:
                titleResId = getReviewFragment().getTitleResId();
                break;

            default:
                Log.d(TAG, "unknown position getPageTitle: " + position);
                return null;
        }

        return mActivity.getString(titleResId);
    }

    public OverviewFragment getOverviewFragment() {
        return (OverviewFragment) getItem(OverviewFragment.INDEX);
    }

    public StatsFragment getStatsFragment() {
        return (StatsFragment) getItem(StatsFragment.INDEX);
    }

    public ReviewFragment getReviewFragment() {
        return (ReviewFragment) getItem(ReviewFragment.INDEX);
    }

    public View getTabView(int position) {
        View view = View.inflate(mActivity, R.layout.common_tab, null);
        TextView titleView = (TextView) view.findViewById(R.id.info);
        titleView.setText(getPageTitle(position));
        return view;
    }

    public int getPageIndexForTag(Object tag) {
        TabbedFragment tabbedFragment = getOverviewFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getStatsFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getReviewFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        throw new IllegalArgumentException("Couldn't find tag: " + tag);
    }

    private boolean tagMatches(Object tag, TabbedFragment tabbedFragment) {
        Integer otherTag = tabbedFragment.getTitleResId();
        return otherTag.equals(tag);
    }
}


