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
import com.lilliemountain.reportcard.model.Syllabus;
import com.lilliemountain.reportcard.model.TimeTable;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Bootstrap;

public class MarkDownActivity extends AppCompatActivity {
    String markDown;
    MarkdownView markdownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_mark_down);
        getAcademicCalender();


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
        finishAfterTransition();
    }
}
