package com.lilliemountain.reportcard.activity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.ReportCardManager;
import com.lilliemountain.reportcard.model.Child;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener {
    Child child;
    String sch,schoolKey;
    TextView name,school,grade,classs;
    String rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_child);
        name=findViewById(R.id.name);
        school=findViewById(R.id.school);
        grade=findViewById(R.id.grade);
        classs=findViewById(R.id.classs);

        ReportCardManager.initializeInstance(this);
        child=new Child();
        child.setChildGrade(ReportCardManager.getInstance().getValue("getChildGrade"));
        child.setChildAge(ReportCardManager.getInstance().getValue("getChildAge"));
        child.setChildClass(ReportCardManager.getInstance().getValue("getChildClass"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setChildName(ReportCardManager.getInstance().getValue("getChildName"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setRollNo((ReportCardManager.getInstance().getValue("getRollNo")));

        sch=ReportCardManager.getInstance().getValue("sch");
        schoolKey=ReportCardManager.getInstance().getValue("schoolKey");

        getSupportActionBar().setTitle(child.getChildName());
        getSupportActionBar().setSubtitle(getString(R.string.rollno)+" "+child.getRollNo());
        name.setText(child.getChildName());
        school.setText(sch);
        grade.setText(child.getChildGrade());
        classs.setText(child.getChildClass());
        rollno=child.getRollNo();
        findViewById(R.id.attendance).setOnClickListener(this);
        findViewById(R.id.syllabus).setOnClickListener(this);
        findViewById(R.id.timetable).setOnClickListener(this);
        findViewById(R.id.academiccalender).setOnClickListener(this);
        findViewById(R.id.progressreport).setOnClickListener(this);
        findViewById(R.id.parentteacherforum).setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
    @Override
    protected void onPause() { super.onPause(); }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.attendance:
                ActivityOptionsCompat attendance = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "attendance");
                startActivity(new Intent(ChildActivity.this,AttendanceActivity.class)
                        .putExtra("schoolKey",schoolKey)
                        .putExtra("rollno",rollno),attendance.toBundle());
                break;
            case R.id.syllabus:
                ActivityOptionsCompat syllabus = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "syllabus");
                startActivity(new Intent(ChildActivity.this,SyllabusActivity.class)
                        .putExtra("schoolKey",schoolKey)
                        .putExtra("rollno",rollno)
                        .putExtra("grade",child.getChildGrade()),syllabus.toBundle());
                break;
            case R.id.timetable:
                ActivityOptionsCompat timetable = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "timetable");
                startActivity(new Intent(ChildActivity.this, TimeTableActivity.class)
                        .putExtra("schoolKey",schoolKey)
                        .putExtra("rollno",rollno)
                        .putExtra("grade",child.getChildGrade()),timetable.toBundle());
                break;
            case R.id.academiccalender:
                ActivityOptionsCompat academiccalender = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "markDown");
                startActivity(new Intent(ChildActivity.this, MarkDownActivity.class).putExtra("schoolKey",schoolKey),academiccalender.toBundle());
                break;
            case R.id.progressreport:
                ActivityOptionsCompat progressreport = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "markDown");
                startActivity(new Intent(ChildActivity.this, ProgressReportActivity.class).putExtra("schoolKey",schoolKey),progressreport.toBundle());
                break;
            case R.id.parentteacherforum:
                ActivityOptionsCompat parentteacherforum = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "parentteacherforum");
                startActivity(new Intent(ChildActivity.this, ParentTeacherForumActivity.class).putExtra("schoolKey",schoolKey),parentteacherforum.toBundle());
                break;
        }
    }

}
