package com.lilliemountain.guardian.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.Syllabus_;

import java.util.ArrayList;

public class TestSyllabusAdapter extends RecyclerView.Adapter<TestSyllabusAdapter.TestSyllabusHolder> {
    ArrayList<Syllabus_> syllabusList=new ArrayList<>();

    public TestSyllabusAdapter(ArrayList<Syllabus_> syllabusList) {
        this.syllabusList = syllabusList;
    }

    @NonNull
    @Override
    public TestSyllabusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test_syllabus,viewGroup,false);
        return new TestSyllabusHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TestSyllabusHolder testSyllabus_, int i) {
        testSyllabus_.sub.setText(syllabusList.get(i).getSubject());
        testSyllabus_.chp.setText(syllabusList.get(i).getSyllabus());
        testSyllabus_.faculty.setText(syllabusList.get(i).getSubjectTeacher());
        testSyllabus_.notes.setText(syllabusList.get(i).getTeacherComments());
    }

    @Override
    public int getItemCount() {
        return syllabusList.size();
    }

    public class TestSyllabusHolder extends RecyclerView.ViewHolder {
        TextView sub,chp,faculty,notes;
        public TestSyllabusHolder(@NonNull View itemView) {
            super(itemView);
            sub=itemView.findViewById(R.id.sub);
            chp=itemView.findViewById(R.id.chp);
            faculty=itemView.findViewById(R.id.faculty);
            notes=itemView.findViewById(R.id.notes);
        }
    }
}
