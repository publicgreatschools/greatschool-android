package com.greatschool.android.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;
import com.greatschool.android.ui.greatkid.GreatKidFragment;
import com.greatschool.android.ui.nearby.NearbyFragment;
import com.greatschool.android.ui.school.MySchoolFragment;
import com.greatschool.android.ui.search.SearchFragment;

import java.util.List;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "HomePagerAdapter";
    private final List<TabbedFragment> mFragments;
    private final FragmentActivity mActivity;

    public HomePagerAdapter(FragmentActivity activity) {
        this(activity, ImmutableList.<TabbedFragment>builder()
                .add(new NearbyFragment())
                .add(new SearchFragment())
                .add(new MySchoolFragment())
                .add(new GreatKidFragment())
                .build());
    }

    private HomePagerAdapter(FragmentActivity activity, List<TabbedFragment> fragments) {
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
            case NearbyFragment.INDEX:
                titleResId = getNearbyFragment().getTitleResId();
                break;
            case SearchFragment.INDEX:
                titleResId = getSearchFragment().getTitleResId();
                break;
            case MySchoolFragment.INDEX:
                titleResId = getMySchoolFragment().getTitleResId();
                break;
            case GreatKidFragment.INDEX:
                titleResId = getGreatKidFragment().getTitleResId();
                break;

            default:
                Log.d(TAG, "unknown position getPageTitle: " + position);
                return null;
        }

        return mActivity.getString(titleResId);
    }

    public NearbyFragment getNearbyFragment() {
        return (NearbyFragment) getItem(NearbyFragment.INDEX);
    }

    public SearchFragment getSearchFragment() {
        return (SearchFragment) getItem(SearchFragment.INDEX);
    }

    public MySchoolFragment getMySchoolFragment() {
        return (MySchoolFragment) getItem(MySchoolFragment.INDEX);
    }

    public GreatKidFragment getGreatKidFragment() {
        return (GreatKidFragment) getItem(GreatKidFragment.INDEX);
    }

    public View getTabView(int position) {
        View view = View.inflate(mActivity, R.layout.tab, null);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView titleView = (TextView) view.findViewById(R.id.info);

        iconView.setImageResource(getPageIconId(position));
        titleView.setText(getPageTitle(position));
        return view;
    }

    private int getPageIconId(int position) {
        switch (position) {
            case NearbyFragment.INDEX:
                return getNearbyFragment().getIconResId();
            case SearchFragment.INDEX:
                return getSearchFragment().getIconResId();
            case MySchoolFragment.INDEX:
                return getMySchoolFragment().getIconResId();
            case GreatKidFragment.INDEX:
                return getGreatKidFragment().getIconResId();
            default:
                Log.d(TAG, "unknown position getPageIconId: " + position);
                return 0;
        }
    }

    public int getPageIndexForTag(Object tag) {
        TabbedFragment tabbedFragment = getNearbyFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getSearchFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getMySchoolFragment();

        if (tagMatches(tag, tabbedFragment)) {
            return tabbedFragment.getIndex();
        }

        tabbedFragment = getGreatKidFragment();

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
