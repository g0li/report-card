package com.lilliemountain.guardian.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.TimeTable_;

import java.util.List;


public class TimeTableTestAdapter extends RecyclerView.Adapter<TimeTableTestAdapter.TTTHolder> {
    List<TimeTable_> list;

    public TimeTableTestAdapter(List<TimeTable_> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TimeTableTestAdapter.TTTHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_time_table_test,viewGroup,false);
        return new TTTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableTestAdapter.TTTHolder tttHolder, int i) {
            tttHolder.date.setText(list.get(i).getDate());
            tttHolder.subject.setText(list.get(i).getSubject());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TTTHolder extends RecyclerView.ViewHolder {
        TextView date,subject;
        public TTTHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            subject=itemView.findViewById(R.id.subject);
        }
    }
}
