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
import com.greatschool.android.ui.common.TabbedFragment;

public class DistanceFragment extends TabbedFragment {
    public static final int INDEX = 0;
    private static final String TAG = "DistanceFragment";

    private RecyclerView mRecyclerView;
    private SchoolAdapter mSchoolAdapter;
    private ViewGroup mMapLayout;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ImageView mMapImage;

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.distance;
    }

    @Override
    public int getIconResId() {
        return 0;
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
        mSchoolAdapter = new SchoolAdapter(Application.getInstance().getSchools(), getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSchoolAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mMapLayout = (ViewGroup) view.findViewById(R.id.map);

        mMapLayout.setVisibility(View.GONE);

        mMapImage = (ImageView) view.findViewById(R.id.map_image);


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
