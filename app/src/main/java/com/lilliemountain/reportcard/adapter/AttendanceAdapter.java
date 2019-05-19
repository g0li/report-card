package com.lilliemountain.reportcard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.model.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceHolder> {

    List<Attendance> attendanceList=new ArrayList<>();
    List<String> listofmonths=new ArrayList<>();

    public AttendanceAdapter(List<Attendance> attendanceList, List<String> listofmonths) {
        this.attendanceList = attendanceList;
        this.listofmonths = listofmonths;
    }

    @NonNull
    @Override
    public AttendanceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_attendance,viewGroup,false);
        return new AttendanceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceHolder attendanceHolder, int i) {
        attendanceHolder.attendance.setText(attendanceList.get(i).getAttendance());
        attendanceHolder.monthyear.setText(listofmonths.get(i));
    }

    @Override
    public int getItemCount() {
        return listofmonths.size();
    }

    public class AttendanceHolder extends RecyclerView.ViewHolder {
        TextView monthyear,attendance;
        public AttendanceHolder(@NonNull View itemView) {
            super(itemView);
            monthyear=itemView.findViewById(R.id.monthyear);
            attendance=itemView.findViewById(R.id.attendance);
        }
    }
}
