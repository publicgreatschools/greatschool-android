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
        super(activity.getSupportFragmentManager());
        SearchListFragment distanceFragment = new SearchListFragment();
        distanceFragment.setSearchSortType(SearchSortType.DISTANCE);

        SearchListFragment azFragment = new SearchListFragment();
        azFragment.setSearchSortType(SearchSortType.AZ);

        SearchListFragment ratingFragment = new SearchListFragment();
        ratingFragment.setSearchSortType(SearchSortType.RATING);

        mFragments = ImmutableList.<TabbedFragment>builder()
                .add(distanceFragment)
                .add(azFragment)
                .add(ratingFragment)
                .build();
        mActivity = activity;
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
            case SearchListFragment.DISTANCE_INDEX:
                titleResId = getDistanceFragment().getTitleResId();
                break;
            case SearchListFragment.AZ_INDEX:
                titleResId = getAZFragment().getTitleResId();
                break;
            case SearchListFragment.RATING_INDEX:
                titleResId = getRatingFragment().getTitleResId();
                break;

            default:
                Log.d(TAG, "unknown position getPageTitle: " + position);
                return null;
        }

        return mActivity.getString(titleResId);
    }

    public SearchListFragment getDistanceFragment() {
        return (SearchListFragment) getItem(SearchListFragment.DISTANCE_INDEX);
    }

    public SearchListFragment getAZFragment() {
        return (SearchListFragment) getItem(SearchListFragment.AZ_INDEX);
    }

    public SearchListFragment getRatingFragment() {
        return (SearchListFragment) getItem(SearchListFragment.RATING_INDEX);
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

