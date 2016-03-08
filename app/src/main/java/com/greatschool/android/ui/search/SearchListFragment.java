package com.greatschool.android.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private ViewGroup mMapLayout;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ImageView mMapImage;
    private SearchSortType mSearchSortType;

    private List<School> mSchoolList = new ArrayList<>();

    public void setSearchSortType(SearchSortType searchSortType) {
        mSearchSortType = searchSortType;
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
        return inflater.inflate(R.layout.distance, container, false);
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
        mMapLayout = (ViewGroup) view.findViewById(R.id.map);
        mMapLayout.setVisibility(View.GONE);
        mMapImage = (ImageView) view.findViewById(R.id.map_image);
    }

    private void loadData() {

        List<School> schoolList = Arrays.asList(Application.getInstance().getSchools());


        Collections.sort(schoolList, new Comparator<School>() {
            @Override
            public int compare(School lhs, School rhs) {
                switch (mSearchSortType) {
                    case DISTANCE:
                        return (int) (lhs.getDistance() - rhs.getDistance());
                    case AZ:
                        return lhs.getName().compareTo(rhs.getName());
                    case RATING:
                        return rhs.getReview() - lhs.getReview();
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

    public void hideOrShowMap(boolean showMap) {
        if (showMap) {
            mMapLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    simulateCoordinates();
                }
            }, 1000);
        } else {
            mMapLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void simulateCoordinates() {
        float x = mMapImage.getX();
        float y = mMapImage.getY();
        float width = mMapImage.getWidth();
        float height = mMapImage.getHeight();

        Log.d(TAG, "x: " + x);
        Log.d(TAG, "y: " + y);
        Log.d(TAG, "width: " + width);
        Log.d(TAG, "height: " + height);
    }
}
