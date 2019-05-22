package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;

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
    String rollno;
    List<TimeTable> timeTables=new ArrayList<>();
    TimeTableAdapter timeTableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_time_table);
        recyclerView=findViewById(R.id.recyclerView);
        schoolKey=getIntent().getStringExtra("schoolKey");
        grade=getIntent().getStringExtra("grade");
        rollno=getIntent().getStringExtra("rollno");
        getSupportActionBar().setTitle("Exam Timetable : "+grade);
        getSupportActionBar().setSubtitle("rollno : "+rollno);
        grade=grade.replace(" ","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance();
        timetable=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("timetable");
        timetable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                timeTables.clear();

                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
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
