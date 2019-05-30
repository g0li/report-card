package com.lilliemountain.guardian.activity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.ReportCardManager;
import com.lilliemountain.guardian.model.Child;


public class ChildActivity extends AppCompatActivity implements View.OnClickListener {
    Child child;
    String sch,schoolKey;
    TextView name,school,grade,classs;
    String rollno,nameStr,gradeStr;
    ImageView imageView2;
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
        imageView2=findViewById(R.id.imageView2);

        ReportCardManager.initializeInstance(this);
        child=new Child();
        child.setChildGrade(ReportCardManager.getInstance().getValue("getChildGrade"));
        child.setChildAge(ReportCardManager.getInstance().getValue("getChildAge"));
        child.setChildClass(ReportCardManager.getInstance().getValue("getChildClass"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setChildName(ReportCardManager.getInstance().getValue("getChildName"));
        child.setChildGender(ReportCardManager.getInstance().getValue("getChildGender"));
        child.setRollNo((ReportCardManager.getInstance().getValue("getRollNo")));
        child.setImage(Uri.parse((ReportCardManager.getInstance().getValue("getImage"))));

        sch=ReportCardManager.getInstance().getValue("sch");
        schoolKey=ReportCardManager.getInstance().getValue("schoolKey");

        getSupportActionBar().setTitle(child.getChildName());
        getSupportActionBar().setSubtitle(getString(R.string.rollno)+" "+child.getRollNo());
        name.setText(child.getChildName());
        school.setText(sch);
        grade.setText(child.getChildGrade());
        classs.setText(child.getChildClass());
        rollno=child.getRollNo();
        gradeStr=child.getChildGrade();
        nameStr=child.getChildName();
        findViewById(R.id.attendance).setOnClickListener(this);
        findViewById(R.id.syllabus).setOnClickListener(this);
        findViewById(R.id.timetable).setOnClickListener(this);
        findViewById(R.id.academiccalender).setOnClickListener(this);
        findViewById(R.id.progressreport).setOnClickListener(this);
        findViewById(R.id.parentteacherforum).setOnClickListener(this);
//        Glide.with(imageView2).load(child.getImage()).error(android.R.color.holo_orange_light).into(imageView2);

    }


    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
    @Override
    protected void onPause() { super.onPause(); }


    @Override
    public void onClick(View v) {
        Intent recylcerIntent=new Intent(ChildActivity.this, RecyclerViewActivity.class).putExtra("schoolKey",schoolKey);
        ActivityOptionsCompat recylcerCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "rec");

        switch (v.getId()){

            case R.id.attendance:
                ActivityOptionsCompat attendance = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "attendance");
                startActivity(new Intent(ChildActivity.this,AttendanceActivity.class)
                        .putExtra("schoolKey",schoolKey)
                        .putExtra("grade",gradeStr)
                        .putExtra("name",nameStr)
                        .putExtra("rollno",rollno),attendance.toBundle());
                break;
            case R.id.syllabus:
                startActivity(recylcerIntent.putExtra("name","sylx")
                        .putExtra("rollno",rollno)
                        .putExtra("grade",child.getChildGrade()),recylcerCompat.toBundle());
                break;
            case R.id.timetable:
                startActivity(recylcerIntent.putExtra("name","tt")
                        .putExtra("rollno",rollno)
                        .putExtra("grade",child.getChildGrade()),recylcerCompat.toBundle());
                break;
            case R.id.academiccalender:
                startActivity(recylcerIntent.putExtra("name","academic"),recylcerCompat.toBundle());
                break;
            case R.id.progressreport:
                startActivity(recylcerIntent.putExtra("name",      "prep"),recylcerCompat.toBundle());
                break;
            case R.id.parentteacherforum:
                ActivityOptionsCompat parentteacherforum = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "parentteacherforum");
                startActivity(new Intent(ChildActivity.this, ParentTeacherForumActivity.class).putExtra("schoolKey",schoolKey),parentteacherforum.toBundle());
                break;
        }
    }

}
