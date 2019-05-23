package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.ReportCardManager;
import com.lilliemountain.reportcard.adapter.ProgressReportAdapter;
import com.lilliemountain.reportcard.model.Child;
import com.lilliemountain.reportcard.model.ProgressReport;

import java.util.ArrayList;
import java.util.List;

public class ProgressReportActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference progressreport;
    String schoolKey;
    Child child;
    List<ProgressReport> progressReports=new ArrayList<>();
    ProgressReportAdapter progressReportAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);
        schoolKey = getIntent().getStringExtra("schoolKey");
        recyclerView=findViewById(R.id.recyclerView);
        getSupportActionBar().setTitle("Progress Report");

        ReportCardManager.initializeInstance(this);
        child=new Child();
        child.setChildGrade(ReportCardManager.getInstance().getValue("getChildGrade"));
        child.setChildAge(ReportCardManager.getInstance().getValue("getChildAge"));
        child.setChildClass(ReportCardManager.getInstance().getValue("getChildClass"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setChildName(ReportCardManager.getInstance().getValue("getChildName"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setRollNo((ReportCardManager.getInstance().getValue("getRollNo")));
        getSupportActionBar().setTitle("Progress Report");
        getSupportActionBar().setSubtitle(getString(R.string.rollno)+" "+child.getRollNo());
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        database= FirebaseDatabase.getInstance();
        progressreport=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("progress-reports");

        progressreport.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressReports.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    ProgressReport report=dataSnapshot1.getValue(ProgressReport.class);
                    if (report.getChildGrade().equals(child.getChildGrade())
                            && report.getChildGrade().equals(child.getChildGrade())
                            && report.getRollNo().equals(child.getRollNo()))
                    {
                        progressReports.add(report);
                    }
                }
                progressReportAdapter=new ProgressReportAdapter(progressReports);
                recyclerView.setAdapter(progressReportAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
