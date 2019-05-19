package com.lilliemountain.reportcard.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.activity.MarkDownActivity;
import com.lilliemountain.reportcard.model.TimeTable;

import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimeTableHolder> {
    List<TimeTable> timeTableList;

    public TimeTableAdapter(List<TimeTable> timeTableList) {
        this.timeTableList = timeTableList;
    }

    @NonNull
    @Override
    public TimeTableHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_syllabus,viewGroup,false);
        return new TimeTableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableHolder timeTableHolder, int i) {
        timeTableHolder.testName.setText(timeTableList.get(i).getExamName());
        timeTableHolder.timeTable=timeTableList.get(i);
    }

    @Override
    public int getItemCount() {
        return timeTableList.size();
    }

    public class TimeTableHolder extends ViewHolder implements View.OnClickListener {
        TimeTable timeTable;
        TextView testName;
        public TimeTableHolder(@NonNull View itemView) {
            super(itemView);
            testName=itemView.findViewById(R.id.testName);
            testName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Activity a= (Activity) v.getContext();
            ActivityOptionsCompat timeTableActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a, v, "markDown");
            v.getContext().startActivity(new Intent(v.getContext(), MarkDownActivity.class).putExtra("markDown",timeTable),timeTableActivityOptionsCompat.toBundle());
        }
    }
}
