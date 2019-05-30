package com.lilliemountain.guardian.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.FAQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQActivity extends AppCompatActivity {
    ExpandableListView expListView;
    List<FAQ> questionAnswerList=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        expListView = findViewById(R.id.explsv);
        database=FirebaseDatabase.getInstance();
        setTitle("FAQ and Help Center");
        android();
        findViewById(R.id.android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android();
            }
        });
        findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web();
            }
        });
    }
    private void web(){
        reference=database.getReference().child(getString(R.string.instance)).child("faq").child("web");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionAnswerList.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    FAQ faq=d1.getValue(FAQ.class);
                    questionAnswerList.add(faq);
                }
                expListView.setAdapter(new FAQAdapter(FAQActivity.this,questionAnswerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void android(){
        reference=database.getReference().child(getString(R.string.instance)).child("faq").child("android");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionAnswerList.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    FAQ faq=d1.getValue(FAQ.class);
                    questionAnswerList.add(faq);
                }
                expListView.setAdapter(new FAQAdapter(FAQActivity.this,questionAnswerList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
