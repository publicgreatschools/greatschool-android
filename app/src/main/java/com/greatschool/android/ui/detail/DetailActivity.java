package com.greatschool.android.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.greatschool.android.R;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.BaseActivity;


public class DetailActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private DetailAdapter mDetailAdapter;
    private ViewPager mViewPager;
    private int mCurrentPage;
    private School mSchool;
    private Toolbar mToolbar;

    private static final String SCHOOL = "SCHOOL";

    public static void startDetailActivity(Context context, School school) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(SCHOOL, school);
        context.startActivity(intent);
        startActivityAnimation((Activity) context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.overview_lowercase);


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackPressed();
            }
        });

        mSchool = (School) getIntent().getSerializableExtra(SCHOOL);

        mDetailAdapter = new DetailAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.detail_view_pager);
        mViewPager.setAdapter(mDetailAdapter);

        initTabs();
    }

    private void initTabs() {
        mTabLayout = (TabLayout) findViewById(R.id.detail_tab_layout);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        OverviewFragment overviewFragment = mDetailAdapter.getOverviewFragment();
        overviewFragment.setSchool(mSchool);
        StatsFragment statsFragment = mDetailAdapter.getStatsFragment();
        ReviewFragment reviewFragment = mDetailAdapter.getReviewFragment();
        reviewFragment.setSchool(mSchool);

        TabLayout.Tab overviewTab = createTabForFragment(overviewFragment, mTabLayout);
        TabLayout.Tab statsTab = createTabForFragment(statsFragment, mTabLayout);
        TabLayout.Tab reviewTab = createTabForFragment(reviewFragment, mTabLayout);

        addTabForFragment(overviewTab, overviewFragment, mTabLayout);
        addTabForFragment(statsTab, statsFragment, mTabLayout);
        addTabForFragment(reviewTab, reviewFragment, mTabLayout);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            View tabView = mDetailAdapter.getTabView(i);

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

//        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setTabTextColors(getResources().getColor(R.color.search_tab_text_color),
                getResources().getColor(R.color.search_tab_selected_text_color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Object tag = tab.getTag();

        mCurrentPage = mDetailAdapter.getPageIndexForTag(tag);
        if (mCurrentPage == ReviewFragment.INDEX || mCurrentPage == StatsFragment.INDEX) {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show();
            return;
        }

        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
