package com.lilliemountain.reportcard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.model.ReportCard;

import java.util.ArrayList;

public class TestReportAdapter extends RecyclerView.Adapter<TestReportAdapter.TestReportHolder> {
    public TestReportAdapter(ArrayList<ReportCard> list) {
        this.list = list;
    }

    ArrayList<ReportCard> list;

    @NonNull
    @Override
    public TestReportHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test_report,viewGroup,false);
        return new TestReportHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestReportHolder testReportHolder, int i) {
        testReportHolder.subject.setText(list.get(i).getSubject());
        testReportHolder.fraction.setText("marks "+list.get(i).getMarks()+"/"+list.get(i).getTotalmarks());
        testReportHolder.grade.setText(list.get(i).getGrade());
        testReportHolder.progress.setMax(Integer.parseInt(list.get(i).getTotalmarks()));
        testReportHolder.progress.setProgress(Integer.parseInt(list.get(i).getMarks()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestReportHolder extends RecyclerView.ViewHolder {
        TextView subject,fraction,grade;
        ProgressBar progress;

        public TestReportHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.testName);
            fraction=itemView.findViewById(R.id.fraction);
            grade=itemView.findViewById(R.id.grade);
            progress=itemView.findViewById(R.id.progress);
        }
    }
}
