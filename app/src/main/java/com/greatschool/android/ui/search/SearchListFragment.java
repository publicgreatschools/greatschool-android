package com.greatschool.android.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greatschool.android.Application;
import com.greatschool.android.R;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.TabbedFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchListFragment extends TabbedFragment {
    private static final String TAG = "DistanceFragment";

    public static final int DISTANCE_INDEX = 0;
    public static final int AZ_INDEX = 1;
    public static final int RATING_INDEX = 2;

    private RecyclerView mRecyclerView;
    private SchoolAdapter mSchoolAdapter;

    private SearchSortType mSearchSortType;

    private List<School> mSchoolList = new ArrayList<>();

    private String mSearchInfo;

    public void setSearchSortType(SearchSortType searchSortType) {
        mSearchSortType = searchSortType;
    }

    public void setSearchInfo(String searchInfo) {
        mSearchInfo = searchInfo;

        loadData();
    }

    @Override
    public int getIndex() {
        switch (mSearchSortType) {
            case DISTANCE:
                return 0;
            case AZ:
                return 1;
            case RATING:
                return 2;
            default:
                break;
        }
        return -1;
    }

    @Override
    public int getTitleResId() {
        switch (mSearchSortType) {
            case DISTANCE:
                return R.string.distance;
            case AZ:
                return R.string.az;
            case RATING:
                return R.string.ratings;
            default:
                break;
        }
        return -1;
    }

    @Override
    public int getIconResId() {
        return 0;
    }

    @Override
    public void registerListenersAndReload() {
        loadData();
    }

    @Override
    public void unregisterListenersAndDestroyLoaders() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mSchoolAdapter = new SchoolAdapter(mSchoolList, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSchoolAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadData() {
        List<School> schoolList;

        if (!TextUtils.isEmpty(mSearchInfo)) {
            schoolList = new ArrayList<>();
            for (School school : Application.getInstance().getSchools()) {
                if (school.getName().toLowerCase().contains(mSearchInfo.toLowerCase())) {
                    schoolList.add(school);
                }
            }
        } else {
            schoolList = Arrays.asList(Application.getInstance().getSchools());
        }

        Collections.sort(schoolList, new Comparator<School>() {
            @Override
            public int compare(School lhs, School rhs) {
                switch (mSearchSortType) {
                    case DISTANCE:
                        return (int) (lhs.getDistance() - rhs.getDistance());
                    case AZ:
                        return lhs.getName().compareTo(rhs.getName());
                    case RATING:
                        return rhs.getScore() - lhs.getScore();
                    default:
                        break;
                }
                return 0;
            }
        });

        mSchoolList.clear();
        mSchoolList.addAll(schoolList);
        mSchoolAdapter.notifyDataSetChanged();
    }
}
