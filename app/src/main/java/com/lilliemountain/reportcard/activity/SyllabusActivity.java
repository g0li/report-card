package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.ReportCardManager;
import com.lilliemountain.reportcard.adapter.SyllabusAdapter;
import com.lilliemountain.reportcard.model.ReportCard;
import com.lilliemountain.reportcard.model.Syllabus;
import com.lilliemountain.reportcard.model.Syllabus_;
import com.lilliemountain.reportcard.model.UpperSyllabus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SyllabusActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference gradeRef;
    String schoolKey,grade;
    String rollno;
    RecyclerView recyclerView;
    SyllabusAdapter syllabusAdapter;
    ArrayList<UpperSyllabus> upperSyllabi=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_syllabus);
        ReportCardManager.initializeInstance(this);
        schoolKey=getIntent().getStringExtra("schoolKey");
        grade=getIntent().getStringExtra("grade");
        rollno=getIntent().getStringExtra("rollno");

        if (schoolKey==null)
        {
            schoolKey=ReportCardManager.getInstance().getValue("schoolKey");
            grade=ReportCardManager.getInstance().getValue("grade");
            rollno=ReportCardManager.getInstance().getValue("rollno");
        }
        else
        {
            ReportCardManager.getInstance().setValue("schoolKey",schoolKey);
            ReportCardManager.getInstance().setValue("rollno",rollno);
            ReportCardManager.getInstance().setValue("grade",grade);
        }
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setTitle(getString(R.string.grade).toLowerCase()+grade);
        getSupportActionBar().setSubtitle(getString(R.string.rollno).toLowerCase()+rollno);
        database=FirebaseDatabase.getInstance();
        gradeRef=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("syllabus");
        Query myTopPostsQuery = gradeRef.orderByChild("childGrade").equalTo(grade);
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e( "dataSnapshot: ", dataSnapshot.getChildrenCount()+"");

                for (DataSnapshot d2 :
                        dataSnapshot.getChildren()) {
                    Syllabus syllabus=d2.getValue(Syllabus.class);
                    Log.e( "syllabus: ", syllabus.getChildGrade());
                    ArrayList<Syllabus_> syllabi_=new ArrayList<>();
                    for (DataSnapshot d3 :
                            d2.child("syllabusInfo").getChildren()) {
                        Syllabus_ sylx = d3.getValue(Syllabus_.class);
                        syllabi_.add(sylx);
                    }
                    upperSyllabi.add(new UpperSyllabus(syllabus,syllabi_));
                }
                syllabusAdapter=new SyllabusAdapter(upperSyllabi);
                recyclerView.setAdapter(syllabusAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e( "databaseError: ",databaseError.toString() );
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
