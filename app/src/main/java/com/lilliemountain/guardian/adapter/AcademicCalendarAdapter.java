package com.lilliemountain.guardian.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.AcademicCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AcademicCalendarAdapter extends RecyclerView.Adapter<AcademicCalendarAdapter.AcademicCalendarHolder> {
    List<AcademicCalendar> academicCalendars=new ArrayList<>();

    public AcademicCalendarAdapter(List<AcademicCalendar> academicCalendars) {
        this.academicCalendars = academicCalendars;
    }

    @NonNull
    @Override
    public AcademicCalendarHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_academic_calendar,viewGroup,false);
        return new AcademicCalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademicCalendarHolder academicCalendarHolder, int i) {
        academicCalendarHolder.eventName.setText(academicCalendars.get(i).getEventName());
        String date=academicCalendars.get(i).getDate();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1=dateFormat.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            String monthName = new SimpleDateFormat("MMMM").format(c.getTime()).substring(0,3).toUpperCase();
            academicCalendarHolder.month.setText(monthName);
            date=date.split("/")[0];
            academicCalendarHolder.date.setText(date);

        } catch (Exception e) {
            Log.e( "onBindViewHolder: ",e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return academicCalendars.size();
    }

    public class AcademicCalendarHolder extends RecyclerView.ViewHolder {
        TextView month,date,eventName;
        public AcademicCalendarHolder(@NonNull View itemView) {
            super(itemView);
            eventName=itemView.findViewById(R.id.eventName);
            date=itemView.findViewById(R.id.date);
            month=itemView.findViewById(R.id.month);
        }
    }
}
