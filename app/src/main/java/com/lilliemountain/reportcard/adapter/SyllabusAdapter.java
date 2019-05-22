package com.lilliemountain.reportcard.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.activity.TestSyllabusActivity;
import com.lilliemountain.reportcard.model.Syllabus_;
import com.lilliemountain.reportcard.model.UpperSyllabus;

import java.util.ArrayList;
public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusHolder> {
    public SyllabusAdapter(ArrayList<UpperSyllabus> syllabusList    ) {
        this.syllabusList = syllabusList;
    }
    ArrayList<UpperSyllabus>syllabusList=new ArrayList<>();
    ArrayList<Syllabus_> s=new ArrayList<>();

    @NonNull
    @Override
    public SyllabusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_syllabus,viewGroup,false);
        return new SyllabusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusHolder syllabusHolder, int i) {
        syllabusHolder.testName.setText("•  "+syllabusList.get(i).getSyllabus().getTestName());
        syllabusHolder.testNames=syllabusList.get(i).getSyllabus().getTestName();
        syllabusHolder.position=i;

    }

    @Override
    public int getItemCount() {
        return syllabusList.size();
    }

    public class SyllabusHolder extends ViewHolder implements View.OnClickListener {
        int position;
        TextView testName;
        String testNames;
        public SyllabusHolder(@NonNull View itemView) {
            super(itemView);
            testName=itemView.findViewById(R.id.testName);
            testName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            s.clear();
            s.addAll(syllabusList.get(position).getList());
            Activity a= (Activity) v.getContext();
            ActivityOptionsCompat attendance = ActivityOptionsCompat.makeSceneTransitionAnimation(a, v, "testAct");
            v.getContext().startActivity(new Intent(v.getContext(), TestSyllabusActivity.class)
                    .putExtra("testName",testNames)
                    .putExtra("s",s),attendance.toBundle());
        }
    }
}
