package com.greatschool.android.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.greatschool.android.R;
import com.greatschool.android.ui.common.BaseActivity;
import com.greatschool.android.ui.greatkid.GreatKidFragment;
import com.greatschool.android.ui.nearby.NearbyFragment;
import com.greatschool.android.ui.school.MySchoolFragment;
import com.greatschool.android.ui.search.SearchFragment;


public class HomeActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private HomePagerAdapter mHomePagerAdapter;
    private ViewPager mViewPager;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        mHomePagerAdapter = new HomePagerAdapter(this);

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mHomePagerAdapter);
        initTabs();
    }

    private void initTabs() {
        mTabLayout = (TabLayout) findViewById(R.id.home_activity_tab);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        NearbyFragment nearbyFragment = mHomePagerAdapter.getNearbyFragment();
        SearchFragment searchFragment = mHomePagerAdapter.getSearchFragment();
        MySchoolFragment mySchoolFragment = mHomePagerAdapter.getMySchoolFragment();
        GreatKidFragment greatKidFragment = mHomePagerAdapter.getGreatKidFragment();

        TabLayout.Tab nearbyTab = createTabForFragment(nearbyFragment, mTabLayout);
        TabLayout.Tab searchTab = createTabForFragment(searchFragment, mTabLayout);
        TabLayout.Tab mySchoolTab = createTabForFragment(mySchoolFragment, mTabLayout);
        TabLayout.Tab greatKidTab = createTabForFragment(greatKidFragment, mTabLayout);

        addTabForFragment(nearbyTab, nearbyFragment, mTabLayout);
        addTabForFragment(searchTab, searchFragment, mTabLayout);
        addTabForFragment(mySchoolTab, mySchoolFragment, mTabLayout);
        addTabForFragment(greatKidTab, greatKidFragment, mTabLayout);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            View tabView = mHomePagerAdapter.getTabView(i);

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

    private void findViews() {
        mViewPager = (ViewPager) findViewById(R.id.home_activity_customviewpager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Object tag = tab.getTag();

        mCurrentPage = mHomePagerAdapter.getPageIndexForTag(tag);

        if (mCurrentPage == MySchoolFragment.INDEX || mCurrentPage == GreatKidFragment.INDEX) {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show();
            return;
        }

        mViewPager.setCurrentItem(mCurrentPage);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Object tag = tab.getTag();

        mCurrentPage = mHomePagerAdapter.getPageIndexForTag(tag);

        if (mCurrentPage == 0) {
            NearbyFragment nearbyFragment = mHomePagerAdapter.getNearbyFragment();
            nearbyFragment.verifyShowHomeStart();
        }
    }
}
