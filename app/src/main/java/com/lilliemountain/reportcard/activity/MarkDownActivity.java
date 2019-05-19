package com.lilliemountain.reportcard.activity;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.model.ProgressReport;
import com.lilliemountain.reportcard.model.Syllabus;
import com.lilliemountain.reportcard.model.TimeTable;

import java.sql.Time;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.StyleSheet;
import br.tiagohm.markdownview.css.styles.Bootstrap;

public class MarkDownActivity extends AppCompatActivity {
    Syllabus syllabus;
    TimeTable timeTable;
    ProgressReport progressReport;
    String markDown;
    MarkdownView markdownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_mark_down);
        try {
            Parcelable parcelable =getIntent().getParcelableExtra("markDown");
            if(parcelable instanceof Syllabus)
                isSysllabus();
            else if(parcelable instanceof ProgressReport)
                isProgressReport();
            else if(parcelable instanceof TimeTable)
                isTimeTable();
            else
                getAcademicCalender();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void isProgressReport() {
        progressReport=getIntent().getParcelableExtra("markDown");
        markDown=progressReport.getReportcard();
        setTitle("Progress Report of "+progressReport.getTestName());
        markDown=markDown.replace("_b","\n");
        markdownView=findViewById(R.id.markdownView);
        markdownView.setEscapeHtml(false);
        markdownView.loadMarkdown(markDown);
    }

    void isSysllabus()
    {
        syllabus=getIntent().getParcelableExtra("markDown");
        markDown=syllabus.getSyllabus();
        setTitle("Syllabus of "+syllabus.getTestName());
        markDown=markDown.replace("_b","\n");
        markdownView=findViewById(R.id.markdownView);
        markdownView.setEscapeHtml(false);
        markdownView.loadMarkdown(markDown);
    }
    void isTimeTable()
    {
        timeTable=getIntent().getParcelableExtra("markDown");
        markDown=timeTable.getTimetable();
        setTitle("Timetable of "+timeTable.getExamName());
        markDown=markDown.replace("_b","\n");
        markdownView=findViewById(R.id.markdownView);
        markdownView.setEscapeHtml(false);
        markdownView.loadMarkdown(markDown);
    }
    void getAcademicCalender(){
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        String schoolKey=getIntent().getStringExtra("schoolKey");
        Log.e("schoolKey: ",schoolKey );
        DatabaseReference asd=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("academiccalendar");
        asd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                markDown=dataSnapshot.getValue().toString();
                setTitle("Academic Calendar");
                markDown=markDown.replace("_b","\n");
                markdownView=findViewById(R.id.markdownView);
                markdownView.setEscapeHtml(false);
                markdownView.loadMarkdown(markDown);
                markdownView.addStyleSheet(new Bootstrap() {});
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
