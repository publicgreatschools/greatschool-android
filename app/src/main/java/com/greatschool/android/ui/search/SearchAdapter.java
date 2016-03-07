package com.greatschool.android.ui.search;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

import java.util.List;

public class SearchAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SearchAdapter";
    private final List<TabbedFragment> mFragments;
    private final FragmentActivity mActivity;

    public SearchAdapter(FragmentActivity activity) {
        this(activity, ImmutableList.<TabbedFragment>builder()
                .add(new DistanceFragment())
                .add(new AZFragment())
                .add(new RatingFragment())
                .build());
    }

    private SearchAdapter(FragmentActivity activity, List<TabbedFragment> fragments) {
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
            case DistanceFragment.INDEX:
                titleResId = getDistanceFragment().getTitleResId();
                break;
            case AZFragment.INDEX:
                titleResId = getAZFragment().getTitleResId();
                break;
            case RatingFragment.INDEX:
                titleResId = getRatingFragment().getTitleResId();
                break;

            default:
                Log.d(TAG, "unknown position getPageTitle: " + position);
                return null;
        }

        return mActivity.getString(titleResId);
    }

    public DistanceFragment getDistanceFragment() {
        return (DistanceFragment) getItem(DistanceFragment.INDEX);
    }

    public AZFragment getAZFragment() {
        return (AZFragment) getItem(AZFragment.INDEX);
    }

    public RatingFragment getRatingFragment() {
        return (RatingFragment) getItem(RatingFragment.INDEX);
    }

    public View getTabView(int position) {
        View view = View.inflate(mActivity, R.layout.search_tab, null);
        TextView titleView = (TextView) view.findViewById(R.id.info);
        titleView.setText(getPageTitle(position));
        return view;
    }

    public int getPageIndexForTag(Object tag) {
        TabbedFragment tabbedFragment = getDistanceFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getAZFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getRatingFragment();

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

