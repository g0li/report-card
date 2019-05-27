package com.lilliemountain.guardian.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.util.TimingLogger;
import android.view.View;
import android.view.ViewTreeObserver;

import com.cooltechworks.views.WhatsAppEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.adapter.ParentTeacherForumAdapter;
import com.lilliemountain.guardian.model.Messages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentTeacherForumActivity extends AppCompatActivity {
    ParentTeacherForumAdapter pTFA;
    FirebaseDatabase database;
    DatabaseReference PTF;
    WhatsAppEditText messageme;
    FloatingActionButton send;
    RecyclerView everything;
    List<Messages> messages=new ArrayList<>();
    List<String> keyList=new ArrayList<>();
    List<Messages> messages2=new ArrayList<>();
    ValueEventListener veL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_teacher_forum);
        send=findViewById(R.id.send);
        everything=findViewById(R.id.everything);
        messageme=findViewById(R.id.messageme);
        everything.setLayoutManager(new LinearLayoutManager(this));
        setTitle(getString(R.string.parent_teacher_forum));
        database=FirebaseDatabase.getInstance();
        final String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        pTFA=new ParentTeacherForumAdapter(messages);
        everything.setAdapter(pTFA);
        String schoolKey= getIntent().getStringExtra("schoolKey");
        PTF=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("parent-teacher-forum");
        veL=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages2.clear();
                messages.clear();
                keyList.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    Messages msg = d1.getValue(Messages.class);
                    if(msg.getEmail().toLowerCase().equals(email.toLowerCase()))
                    {
                        messages.add(msg);
                        keyList.add(d1.getKey());
                    }
                }
                pTFA.notifyDataSetChanged();
                for (Messages msg :
                        messages) {
                    if (!msg.isMymessage())
                        msg.setSeen(true);
                    messages2.add(msg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        PTF.addValueEventListener(veL);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageme.getText().toString().trim().length()>0)
                {
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy EEEE hh:mm a");
                    Messages msg=new Messages(messageme.getText().toString(),formatDate.format(Calendar.getInstance().getTime()),email,false,true);
                    PTF.push().setValue(msg).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            messageme.getText().clear();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        makeSeen();
    }
    private void makeSeen(){
        PTF.removeEventListener(veL);
        Map<String,Object> stringMessagesHashMap=new ArrayMap<>();
        for (int i = 0; i < keyList.size(); i++) {
            stringMessagesHashMap.put(keyList.get(i),messages2.get(i));
        }
        PTF.updateChildren(stringMessagesHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finishAfterTransition();
            }
        });
    }
}
