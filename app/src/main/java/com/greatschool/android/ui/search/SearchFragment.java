package com.greatschool.android.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.greatschool.android.Application;
import com.greatschool.android.R;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.SchoolItem;
import com.greatschool.android.ui.common.TabbedFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SearchFragment extends TabbedFragment implements TabLayout.OnTabSelectedListener {

    public static final int INDEX = 1;
    private static final String TAG = "SearchFragment";
    private TabLayout mTabLayout;
    private SearchAdapter mSearchAdapter;
    private ViewPager mViewPager;
    private int mCurrentPage;
    private ImageView mLocation;
    private FrameLayout mMapLayout;
    private boolean mShownListView = true;
    private FrameLayout mLocationPointLayout;
    private LocationPoint mLocationPoint;
    private LinearLayout mSchoolItemLayout;
    private SchoolItem mSchoolItem;
    private EditText mSearchEdit;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private float mWidth, mHeight;
    private String mSearchInfo;
    private Map<School, Point> mPointMap = new HashMap<>();

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mWidth = mViewPager.getMeasuredWidth();
                mHeight = mViewPager.getMeasuredHeight() - 100;

                for (School school : Application.getInstance().getSchools()) {
                    randomPoint(mPointMap, school, (int) mWidth, (int) mHeight);
                }
            }
        });
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

//                clear();
//
//                if (mShownListView) {
//                    mLocation.setImageResource(R.mipmap.school_view);
//                    mMapLayout.setVisibility(View.VISIBLE);
//                    mTabLayout.setVisibility(View.GONE);
//                    mViewPager.setVisibility(View.GONE);
//
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            loadLocationInMap();
//                        }
//                    }, 200);
//                } else {
//                    mLocation.setImageResource(R.mipmap.search_location);
//                    mMapLayout.setVisibility(View.GONE);
//                    mTabLayout.setVisibility(View.VISIBLE);
//                    mViewPager.setVisibility(View.VISIBLE);
//                }
//
//                mShownListView = !mShownListView;
            }
        });

        mMapLayout = (FrameLayout) view.findViewById(R.id.map);
        mMapLayout.setVisibility(View.GONE);

        mLocationPointLayout = (FrameLayout) view.findViewById(R.id.location_point);
        mSchoolItemLayout = (LinearLayout) view.findViewById(R.id.school_item_layout);
        mMapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        mSearchEdit = (EditText) view.findViewById(R.id.search);
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

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

        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setTabTextColors(getResources().getColor(R.color.search_tab_text_color),
                getResources().getColor(R.color.search_tab_selected_text_color));
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

    private void simulateCoordinates(List<School> schoolList) {

        mLocationPointLayout.removeAllViews();

        for (Map.Entry<School, Point> entry : mPointMap.entrySet()) {
            final School school = entry.getKey();

            if (!schoolList.contains(school)) {
                continue;
            }

            final LocationPoint locationPoint = new LocationPoint(getContext()).create(school);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = entry.getValue().x;
            params.topMargin = entry.getValue().y;
            mLocationPointLayout.addView(locationPoint, params);

            locationPoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mLocationPoint != null && mLocationPoint == locationPoint) {
                        return;
                    }

                    if (mLocationPoint != null) {
                        mLocationPoint.cancelAnimation();
                    }

                    mLocationPoint = locationPoint;
                    mLocationPoint.startAnimation();

                    if (mSchoolItem == null) {
                        SchoolItem schoolItem = new SchoolItem(getContext(), false);
                        schoolItem = schoolItem.create(school);
                        mSchoolItemLayout.addView(schoolItem);

                        mSchoolItem = schoolItem;
                        schoolItem.startAnimation();
                    } else {
                        mSchoolItem.create(school);
                    }
                }
            });
        }
    }

    private void randomPoint(Map<School, Point> pointMap, School school, int width, int height) {

        int randomX = random(0, width);
        int randomY = random(0, height);


        for (Map.Entry<School, Point> entry : pointMap.entrySet()) {

            int a = entry.getValue().x - randomX;
            int b = entry.getValue().y - randomY;
            int c = (int) Math.sqrt(a * a + b * b);

            if (c < 200) {
                randomPoint(pointMap, school,width, height);
                return;
            }
        }

        pointMap.put(school, new Point(randomX, randomY));
    }

    private int random(int start, int end) {
        Random random = new Random();

        start = start + 100;
        end = end - 200;

        return random.nextInt(end - start) + start;
    }

    public void clear() {
        if (mLocationPoint != null) {
            mLocationPoint.cancelAnimation();
            mLocationPoint = null;
        }

        if (mSchoolItem != null) {
            mSchoolItem.cancelAnimation();
            mSchoolItemLayout.removeAllViews();
            mSchoolItem = null;
        }
    }

    private void loadLocationInMap() {
        if (!TextUtils.isEmpty(mSearchInfo)) {
            final List<School> schoolList = new ArrayList<>();
            School[] schools = Application.getInstance().getSchools();

            for (School school : schools) {
                if (school.getName().toLowerCase().contains(mSearchInfo.toLowerCase())) {
                    schoolList.add(school);
                }
            }

            simulateCoordinates(schoolList);
        } else {
            simulateCoordinates(Arrays.asList(Application.getInstance().getSchools()));
        }
    }

    public void search(String searchInfo) {

        mSearchInfo = searchInfo;

        clear();

        loadLocationInMap();

        SearchListFragment distanceFragment = mSearchAdapter.getDistanceFragment();
        SearchListFragment azFragment = mSearchAdapter.getAZFragment();
        SearchListFragment ratingFragment = mSearchAdapter.getRatingFragment();

        distanceFragment.setSearchInfo(mSearchInfo);
        azFragment.setSearchInfo(mSearchInfo);
        ratingFragment.setSearchInfo(mSearchInfo);
    }

}
