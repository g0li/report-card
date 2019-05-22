package com.lilliemountain.reportcard.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeClipBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.ReportCardManager;
import com.lilliemountain.reportcard.adapter.ChildAdapter;
import com.lilliemountain.reportcard.model.Child;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements ChildAdapter.onClick {
    RecyclerView kidslist;
    TextView welcomeuser,checkoutchild;
    ChildAdapter childAdapter;
    FirebaseDatabase database;
    DatabaseReference instance,schools;
    List<Child> children=new ArrayList<>();
    List<String> schoolist=new ArrayList<>();
    String schoolKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_user);
        welcomeuser=findViewById(R.id.welcomeuser);
        checkoutchild=findViewById(R.id.checkoutchild);
        kidslist=findViewById(R.id.kidslist);
        kidslist.setLayoutManager(new GridLayoutManager(this,2));

        database=FirebaseDatabase.getInstance();
        instance=database.getReference(getString(R.string.instance));
        final String EM= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        schools=instance.child("schools");
        schools.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schoolist.clear();
                children.clear();
                String of="";
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    schoolKey=d1.getKey();
                    Log.e( "schoolKey: ",(schoolKey));
                    String schoolName,schoolAddress;
                    DataSnapshot students;
                    schoolName=d1.child("schoolName").getValue().toString();
                    schoolAddress=d1.child("schoolAddress").getValue().toString();
                    Log.e( "schoolName: ",(schoolName));
                    Log.e( "schoolAddress: ",(schoolAddress));

                    students=d1.child("student");
                    for (DataSnapshot d2 :
                            students.getChildren()) {
                        Child child=(d2.getValue(Child.class));
                        if (child.getParentEmail().equals(EM)) {
                            children.add(d2.getValue(Child.class));
                            schoolist.add(schoolName);
                            if(of.length()>1)
                                of=of+" & "+child.getChildName().split(" ")[0];
                            else
                                of=of+child.getChildName().split(" ")[0];
                        }
                    }
                }
                if(of.length()>0)
                welcomeuser.setText("Welcome Guardian of "+of);
                if(children.size()==1)
                checkoutchild.setText("Checkout what your child is up to.");
                else
                checkoutchild.setText("Checkout what your children are up to.");
                childAdapter=new ChildAdapter(children,schoolist,UserActivity.this);
                kidslist.setAdapter(childAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e( "onCancelled: ",databaseError.getMessage() );
            }
        });
    }

    @Override
    public void click(Child child, String school, CardView cardView) {
        ReportCardManager.initializeInstance(this);
        ReportCardManager.getInstance().setValue("getChildGrade",child.getChildGrade());
        ReportCardManager.getInstance().setValue("getChildAge",child.getChildAge());
        ReportCardManager.getInstance().setValue("getChildClass",child.getChildClass());
        ReportCardManager.getInstance().setValue("getChildGender",child.getChildGender());
        ReportCardManager.getInstance().setValue("getChildName",child.getChildName());
        ReportCardManager.getInstance().setValue("getChildGender",child.getChildGender());
        ReportCardManager.getInstance().setValue("getRollNo",child.getRollNo()+"");
        ReportCardManager.getInstance().setValue("sch",school);
        ReportCardManager.getInstance().setValue("schoolKey",schoolKey);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, (View)cardView, "child");
        startActivity(new Intent(UserActivity.this, ChildActivity.class),options.toBundle());
    }
}