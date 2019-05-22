package com.lilliemountain.reportcard.activity;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.TestReportAdapter;
import com.lilliemountain.reportcard.model.ProgressReport;
import com.lilliemountain.reportcard.model.ReportCard;

import java.util.ArrayList;

public class TestReportActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressReport progressReport;
    ArrayList<ReportCard> list=new ArrayList<>();
    TextView tots,grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);
        recyclerView=findViewById(R.id.recyclerView);
        tots=findViewById(R.id.tots);
        grade=findViewById(R.id.grade);
        progressReport=getIntent().getParcelableExtra("progressReport");
        setTitle(progressReport.getTestName());
        list.addAll(progressReport.getReportCard());
        recyclerView.setAdapter(new TestReportAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tots.setText(progressReport.getTotalMarks()+"/"+progressReport.getGrandTotal());
        grade.setText(progressReport.getGrade());
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
