package com.lilliemountain.guardian.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.Child;


import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildHolder> {
    List<Child> children;
    List<String> school;
    onClick click;
    public ChildAdapter(List<Child> children, List<String> school,onClick click) {
        this.children = children;
        this.school=school;
        this.click=click;
    }

    @NonNull
    @Override
    public ChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child,viewGroup,false);
        return new ChildHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildHolder childHolder, int i) {
        childHolder.child=children.get(i);
        childHolder.sch=school.get(i);
        childHolder.name.setText(children.get(i).getChildName());
        childHolder.grade.setText(children.get(i).getChildGrade());
        childHolder.school.setText(school.get(i));
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
    public interface onClick{
        void click(Child child,String school,CardView cardView);
    }
    public class ChildHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,grade,school;
        Child child;
        String sch;
        CardView cardView;
        public ChildHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            school=itemView.findViewById(R.id.school);
            grade=itemView.findViewById(R.id.grade);
            cardView=itemView.findViewById(R.id.cardView);

            name.setOnClickListener(this);
            school.setOnClickListener(this);
            grade.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            click.click(child,sch,cardView);
        }

    }
}
