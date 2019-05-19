package com.lilliemountain.reportcard.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.activity.MarkDownActivity;
import com.lilliemountain.reportcard.model.Syllabus;

import java.util.List;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusHolder> {
    List<Syllabus> syllabusList;

    public SyllabusAdapter(List<Syllabus> syllabusList) {
        this.syllabusList = syllabusList;
    }

    @NonNull
    @Override
    public SyllabusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_syllabus,viewGroup,false);
        return new SyllabusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusHolder syllabusHolder, int i) {
        syllabusHolder.testName.setText(syllabusList.get(i).getTestName());
        syllabusHolder.syllabus=syllabusList.get(i);
    }

    @Override
    public int getItemCount() {
        return syllabusList.size();
    }

    public class SyllabusHolder extends ViewHolder implements View.OnClickListener {
        Syllabus syllabus;
        TextView testName;
        public SyllabusHolder(@NonNull View itemView) {
            super(itemView);
            testName=itemView.findViewById(R.id.testName);
            testName.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Activity a= (Activity) v.getContext();
            ActivityOptionsCompat syllabusActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(a, v, "markDown");

            v.getContext().startActivity(new Intent(v.getContext(), MarkDownActivity.class).putExtra("markDown",syllabus),syllabusActivityOptionsCompat.toBundle());
        }
    }
}
