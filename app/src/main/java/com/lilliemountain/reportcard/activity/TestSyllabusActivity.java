package com.lilliemountain.reportcard.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;

import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.TestSyllabusAdapter;
import com.lilliemountain.reportcard.model.Syllabus;
import com.lilliemountain.reportcard.model.Syllabus_;

import java.util.ArrayList;

public class TestSyllabusActivity extends AppCompatActivity {
    ArrayList<Syllabus_> s=new ArrayList<>();
    RecyclerView recyclerView;
    String testName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_test_syllabus);
        testName=getIntent().getStringExtra("testName");
        setTitle(testName);
        s.addAll(getIntent().<Syllabus_>getParcelableArrayListExtra("s"));
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new TestSyllabusAdapter(s));
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
