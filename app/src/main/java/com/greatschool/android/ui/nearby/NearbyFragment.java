package com.greatschool.android.ui.nearby;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private TextView mSchoolSizeView;
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

    @Override
    public void registerListenersAndReload() {

    }

    @Override
    public void unregisterListenersAndDestroyLoaders() {

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

        mSchoolSizeView = (TextView) view.findViewById(R.id.school_size);

        Shader textShader = new LinearGradient(0, 0, 30, 400,
                new int[]{Color.WHITE, Color.LTGRAY},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        mSchoolSizeView.getPaint().setShader(textShader);

        mHomeStart.setVisibility(View.VISIBLE);
        mHomeFullScreen.setVisibility(View.GONE);
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

        Animation toCurrent = AnimationUtils.loadAnimation(getContext(), R.anim.enter_back);
        mHomeStart.startAnimation(toCurrent);

        Animation toRight = AnimationUtils.loadAnimation(getContext(), R.anim.exit_back);
        toRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHomeFullScreen.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mHomeFullScreen.startAnimation(toRight);
    }

    private void showHomeFullScreen() {
        mHomeFullScreen.setVisibility(View.VISIBLE);

        Animation toLeft = AnimationUtils.loadAnimation(getContext(), R.anim.exit);
        mHomeStart.startAnimation(toLeft);
        toLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHomeStart.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Animation toCurrent = AnimationUtils.loadAnimation(getContext(), R.anim.enter);
        mHomeFullScreen.startAnimation(toCurrent);
    }

    public void verifyShowHomeStart() {
        if (mHomeStart != null && mHomeStart.getVisibility() == View.GONE) {
            showHomeStart();
        }
    }

    private void showSchools() {

        Application application = Application.getInstance();
        School[] schools = application.getSchools();

        int i = 0;
        for (School school : schools) {
            if (i >= 3) {
                break;
            }
            i++;
            SchoolItem schoolItem = new SchoolItem(getContext(), false);
            schoolItem.create(school);
            mAssignedSchoolLayout.addView(schoolItem);
        }

        mAssignedSchoolLayout.addView(new SchoolItem(getContext(), true));
    }
}
