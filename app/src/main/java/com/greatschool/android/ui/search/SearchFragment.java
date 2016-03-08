package com.greatschool.android.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greatschool.android.R;
import com.greatschool.android.ui.common.TabbedFragment;

public class SearchFragment extends TabbedFragment implements TabLayout.OnTabSelectedListener {

    public static final int INDEX = 1;
    private TabLayout mTabLayout;
    private SearchAdapter mSearchAdapter;
    private ViewPager mViewPager;
    private int mCurrentPage;
    private ImageView mLocation;

    private boolean mShownListView = true;

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.search;
    }

    @Override
    public int getIconResId() {
        return R.drawable.search_icon_selector;
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
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        View toolBarView = View.inflate(getActivity(), R.layout.search_toolbar, null);
        toolbar.addView(toolBarView);

        mSearchAdapter = new SearchAdapter(getActivity());

        mViewPager = (ViewPager) view.findViewById(R.id.search_view_pager);

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSearchAdapter);

        initTabs(view);

        mLocation = (ImageView) toolBarView.findViewById(R.id.location);
        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchListFragment distanceFragment = mSearchAdapter.getDistanceFragment();

                if (mShownListView) {
                    mLocation.setImageResource(R.mipmap.school_view);
                    distanceFragment.hideOrShowMap(true);
                    mTabLayout.setVisibility(View.GONE);
                } else {
                    mLocation.setImageResource(R.mipmap.search_location);
                    distanceFragment.hideOrShowMap(false);
                    mTabLayout.setVisibility(View.VISIBLE);
                }

                mShownListView = !mShownListView;
            }
        });
    }

    private void initTabs(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.search_tab);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        SearchListFragment distanceFragment = mSearchAdapter.getDistanceFragment();
        SearchListFragment azFragment = mSearchAdapter.getAZFragment();
        SearchListFragment ratingFragment = mSearchAdapter.getRatingFragment();

        TabLayout.Tab distanceTab = createTabForFragment(distanceFragment);
        TabLayout.Tab azTab = createTabForFragment(azFragment);
        TabLayout.Tab ratingTab = createTabForFragment(ratingFragment);

        addTabForFragment(distanceTab, distanceFragment);
        addTabForFragment(azTab, azFragment);
        addTabForFragment(ratingTab, ratingFragment);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            View tabView = mSearchAdapter.getTabView(i);

            if (tab != null) {
                tab.setCustomView(tabView);
            }

            if (i == 0) {
                tabView.setSelected(true);
            } else {
                tabView.setSelected(false);
            }
        }

        mTabLayout.setOnTabSelectedListener(this);
    }

    @NonNull
    private TabLayout.Tab createTabForFragment(TabbedFragment tabbedFragment) {
        Object tag = tabbedFragment.getTitleResId();
        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setTag(tag);
        return tab;
    }

    private void addTabForFragment(TabLayout.Tab tabToAdd, TabbedFragment tabbedFragmentToAdd) {
        mTabLayout.addTab(tabToAdd, tabbedFragmentToAdd.getIndex());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Object tag = tab.getTag();

        mCurrentPage = mSearchAdapter.getPageIndexForTag(tag);
        mViewPager.setCurrentItem(mCurrentPage);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
