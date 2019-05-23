package com.lilliemountain.reportcard.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.Window;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.TimeTableTestAdapter;
import com.lilliemountain.reportcard.model.TimeTable;
import com.lilliemountain.reportcard.model.TimeTable_;

import java.util.ArrayList;
import java.util.List;

public class TimeTableTestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TimeTable timeTable;
    List<TimeTable_> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_time_table_test);
        timeTable=getIntent().getParcelableExtra("timeTable");
        list.add(new TimeTable_(getString(R.string.boldDate),getString(R.string.boldSub)));
        list.addAll(timeTable.getTimeTable());
        getSupportActionBar().setTitle("t: "+timeTable.getTestName());
        getSupportActionBar().setSubtitle("g: "+timeTable.getChildGrade());
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TimeTableTestAdapter(list));
    }
}
