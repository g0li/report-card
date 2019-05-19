package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.TimeTableAdapter;
import com.lilliemountain.reportcard.model.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTableActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference timetable;
    String schoolKey,grade;
    Integer rollno;
    List<TimeTable> timeTables=new ArrayList<>();
    TimeTableAdapter timeTableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        recyclerView=findViewById(R.id.recyclerView);
        schoolKey=getIntent().getStringExtra("schoolKey");
        grade=getIntent().getStringExtra("grade");
        rollno=getIntent().getIntExtra("rollno",0);
        getSupportActionBar().setTitle("Exam Timetable : "+grade);
        getSupportActionBar().setSubtitle("rollno : "+rollno);
        grade=grade.replace(" ","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance();
        timetable=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("timetable").child(grade);
        timetable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                timeTables.clear();
                Log.e("onDataChange: ", dataSnapshot.getChildrenCount()+"");

                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    Log.e("onDataChange: ", d1.getKey());
                    TimeTable t=d1.getValue(TimeTable.class);
                    timeTables.add(t);
                }
                timeTableAdapter=new TimeTableAdapter(timeTables);
                recyclerView.setAdapter(timeTableAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
