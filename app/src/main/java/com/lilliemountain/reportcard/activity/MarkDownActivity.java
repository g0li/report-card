package com.lilliemountain.reportcard.activity;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.model.Syllabus;
import com.lilliemountain.reportcard.model.TimeTable;

import java.sql.Time;

import br.tiagohm.markdownview.MarkdownView;

public class MarkDownActivity extends AppCompatActivity {
    Syllabus syllabus;
    TimeTable timeTable;
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
            else if(parcelable instanceof TimeTable)
                isTimeTable();
            else
                onBackPressed();
        } catch (Exception e) {
        }

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
    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
