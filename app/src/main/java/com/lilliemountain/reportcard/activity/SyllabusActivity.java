package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.SyllabusAdapter;
import com.lilliemountain.reportcard.model.Syllabus;

import java.util.ArrayList;
import java.util.List;

public class SyllabusActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference gradeRef;
    String schoolKey,grade;
    Integer rollno;
    RecyclerView recyclerView;
    SyllabusAdapter syllabusAdapter;
    List<Syllabus> syllabusList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_syllabus);

        schoolKey=getIntent().getStringExtra("schoolKey");
        grade=getIntent().getStringExtra("grade");
        rollno=getIntent().getIntExtra("rollno",0);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setTitle("grade :"+grade);
        getSupportActionBar().setSubtitle("rollno :"+rollno);
        database=FirebaseDatabase.getInstance();
        grade=grade.replace(" ","");
        gradeRef=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("syllabus").child(grade);
        gradeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                syllabusList.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    Syllabus syllabus = d1.getValue(Syllabus.class);
                    syllabusList.add(syllabus);
                }
                syllabusAdapter=new SyllabusAdapter(syllabusList);
                recyclerView.setAdapter(syllabusAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("onCancelled: ",databaseError.getMessage() );
            }
        });
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
