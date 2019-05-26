package com.lilliemountain.guardian.activity;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.adapter.TestReportAdapter;
import com.lilliemountain.guardian.model.ProgressCard;
import com.lilliemountain.guardian.model.ProgressReport;

import java.util.ArrayList;

public class TestReportActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressReport progressReport;
    ArrayList<ProgressCard> list=new ArrayList<>();
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
        list.addAll(progressReport.getProgressCard());
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
