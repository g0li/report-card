package com.lilliemountain.reportcard.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.activity.MarkDownActivity;
import com.lilliemountain.reportcard.activity.TestReportActivity;
import com.lilliemountain.reportcard.model.ProgressReport;
import com.lilliemountain.reportcard.model.ReportCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProgressReportAdapter extends RecyclerView.Adapter<ProgressReportAdapter.ProgressReportHolder> {
    List<ProgressReport> progressReports;

    public ProgressReportAdapter(List<ProgressReport> progressReports) {
        this.progressReports = progressReports;
    }

    @NonNull
    @Override
    public ProgressReportHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_report,viewGroup,false);
        return new ProgressReportHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgressReportHolder progressReportHolder, int i) {
        progressReportHolder.grade.setText(progressReports.get(i).getGrade());
        progressReportHolder.testname.setText(progressReports.get(i).getTestName());
        progressReportHolder.progressReport=(progressReports.get(i));
        progressReportHolder.marks.setText(progressReports.get(i).getTotalMarks()+"/"+progressReports.get(i).getGrandTotal());
    }

    @Override
    public int getItemCount() {
        return progressReports.size();
    }

    public class ProgressReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView grade,testname,marks;
        ProgressReport progressReport;
        public ProgressReportHolder(@NonNull View itemView) {
            super(itemView);
            grade=itemView.findViewById(R.id.grade);
            testname=itemView.findViewById(R.id.testname);
            marks=itemView.findViewById(R.id.marks);
            itemView.setOnClickListener(this);
            grade.setOnClickListener(this);
            testname.setOnClickListener(this);
            marks.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Activity a= (Activity) v.getContext();
            ActivityOptionsCompat progressReportActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a, v, "progressReport");
            v.getContext().startActivity(new Intent(v.getContext(), TestReportActivity.class).putExtra("progressReport",progressReport),progressReportActivityOptionsCompat.toBundle());
        }
    }
}
