package com.lilliemountain.guardian.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.ReportCardManager;
import com.lilliemountain.guardian.adapter.AcademicCalendarAdapter;
import com.lilliemountain.guardian.adapter.ProgressReportAdapter;
import com.lilliemountain.guardian.adapter.SyllabusAdapter;
import com.lilliemountain.guardian.adapter.TimeTableAdapter;
import com.lilliemountain.guardian.model.AcademicCalendar;
import com.lilliemountain.guardian.model.Child;
import com.lilliemountain.guardian.model.ProgressReport;
import com.lilliemountain.guardian.model.Syllabus;
import com.lilliemountain.guardian.model.Syllabus_;
import com.lilliemountain.guardian.model.TimeTable;
import com.lilliemountain.guardian.model.UpperSyllabus;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    /****UNIVERSAL****/
    String decider;
    String schoolKey;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView recyclerView;

    /****FOR ACADEMIC CALENDAR****/
    Child child;
    List<ProgressReport> progressReports=new ArrayList<>();

    /****FOR ACADEMIC CALENDAR****/
    List<AcademicCalendar> academicCalendars=new ArrayList<>();

    /****FOR SYLLABUS****/
    String rollno,grade;
    ArrayList<UpperSyllabus> upperSyllabi=new ArrayList<>();

    /****FOR TIMETABLE****/
    List<TimeTable> timeTables=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView=findViewById(R.id.recyclerView);
        database=FirebaseDatabase.getInstance();
        schoolKey=getIntent().getStringExtra("schoolKey");
        decider=getIntent().getStringExtra("name");

        switch (decider){
            case "academic":
                CallAcademics();
                break;
            case "prep":
                CallProgressReport();
            break;
            case "sylx":
                CallSyllabus();
            break;
            case "tt":
                CallTimeTable();
            break;
        }
    }

    private void CallAcademics(){
        getSupportActionBar().setTitle("Academic Calendar");
        reference=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("academiccalendar");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                academicCalendars.clear();
                Log.e( schoolKey,dataSnapshot.getKey()+"" );
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    AcademicCalendar ac = d1.getValue(AcademicCalendar.class);
                    academicCalendars.add(ac);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
                recyclerView.setAdapter(new AcademicCalendarAdapter(academicCalendars));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e( schoolKey,databaseError.getMessage()+"" );

            }
        });
    }
    private void CallProgressReport() {

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
        reference=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("progress-reports");

        reference.addValueEventListener(new ValueEventListener() {
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
                recyclerView.setAdapter(new ProgressReportAdapter(progressReports));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void CallSyllabus() {
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
        reference=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("syllabus");
        Query myTopPostsQuery = reference.orderByChild("childGrade").equalTo(grade);
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d2 :
                        dataSnapshot.getChildren()) {
                    Syllabus syllabus=d2.getValue(Syllabus.class);
                    ArrayList<Syllabus_> syllabi_=new ArrayList<>();
                    for (DataSnapshot d3 :
                            d2.child("syllabusInfo").getChildren()) {
                        Syllabus_ sylx = d3.getValue(Syllabus_.class);
                        syllabi_.add(sylx);
                    }
                    upperSyllabi.add(new UpperSyllabus(syllabus,syllabi_));
                }
                recyclerView.setAdapter(new SyllabusAdapter(upperSyllabi));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void CallTimeTable() {
        recyclerView=findViewById(R.id.recyclerView);
        schoolKey=getIntent().getStringExtra("schoolKey");
        grade=getIntent().getStringExtra("grade");
        rollno=getIntent().getStringExtra("rollno");
        getSupportActionBar().setTitle("Exam Timetable : "+grade);
        getSupportActionBar().setSubtitle("rollno : "+rollno);
        grade=grade.replace(" ","");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance();
        reference=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("timetable");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                timeTables.clear();

                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    TimeTable t=d1.getValue(TimeTable.class);
                    timeTables.add(t);
                }
                recyclerView.setAdapter(new TimeTableAdapter(timeTables));
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
