package com.greatschool.android.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.greatschool.android.model.School;
import com.greatschool.android.ui.common.SchoolItem;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<School> mSchools;
    private final Context mContext;

    public SchoolAdapter(List<School> schools, Context context) {
        mSchools = schools;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SchoolItem schoolItem = new SchoolItem(mContext, false);

        return new DistanceItem(schoolItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        School school = mSchools.get(position);

        SchoolItem schoolItem = (SchoolItem) holder.itemView;
        schoolItem.create(school);
    }

    @Override
    public int getItemCount() {
        return mSchools.size();
    }

    static class DistanceItem extends RecyclerView.ViewHolder {

        public DistanceItem(View itemView) {
            super(itemView);
        }
    }
}
