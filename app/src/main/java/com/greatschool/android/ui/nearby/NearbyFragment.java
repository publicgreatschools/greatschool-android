package com.greatschool.android.ui.nearby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greatschool.android.Application;
import com.greatschool.android.R;
import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.SchoolItem;
import com.greatschool.android.ui.common.TabbedFragment;


public class NearbyFragment extends TabbedFragment implements View.OnClickListener {

    public static final int INDEX = 0;
    private Button mExplore;
    private ViewGroup mHomeStart, mHomeFullScreen;
    private LinearLayout mAssignedSchoolLayout;

    @Override
    public int getIndex() {
        return INDEX;
    }

    @Override
    public int getTitleResId() {
        return R.string.nearby;
    }

    @Override
    public int getIconResId() {
        return R.drawable.nearby_icon_selector;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearby, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView schoolDes = (TextView) view.findViewById(R.id.school_des);
        schoolDes.setText(Html.fromHtml(getString(R.string.school_des)));

        mExplore = (Button) view.findViewById(R.id.explore);
        mHomeStart = (ViewGroup) view.findViewById(R.id.home_start);
        mHomeFullScreen = (ViewGroup) view.findViewById(R.id.home_fullscreen);
        mExplore.setOnClickListener(this);

        mAssignedSchoolLayout = (LinearLayout) view.findViewById(R.id.assigned_schools);

        showHomeStart();

        showSchools();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.explore:
                showHomeFullScreen();
                break;

            default:

                break;
        }
    }

    private void showHomeStart() {
        mHomeStart.setVisibility(View.VISIBLE);
        mHomeFullScreen.setVisibility(View.GONE);
    }

    private void showHomeFullScreen() {
        mHomeStart.setVisibility(View.GONE);
        mHomeFullScreen.setVisibility(View.VISIBLE);
    }

    public void verifyShowHomeStart() {
        if (mHomeStart != null && mHomeStart.getVisibility() == View.GONE) {
            showHomeStart();
        }
    }

    private void showSchools() {

        Application application = Application.getInstance();
        School[] schools = application.getSchools();

        for (School school : schools) {
            SchoolItem schoolItem = new SchoolItem(getContext());
            schoolItem.create(school);
            mAssignedSchoolLayout.addView(schoolItem);
        }
    }
}
