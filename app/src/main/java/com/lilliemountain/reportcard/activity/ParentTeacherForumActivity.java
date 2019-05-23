package com.lilliemountain.reportcard.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cooltechworks.views.WhatsAppEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.adapter.ParentTeacherForumAdapter;
import com.lilliemountain.reportcard.model.Messages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParentTeacherForumActivity extends AppCompatActivity {
    ParentTeacherForumAdapter pTFA;
    FirebaseDatabase database;
    DatabaseReference PTF;
    WhatsAppEditText messageme;
    FloatingActionButton send;
    RecyclerView everything;
    List<Messages> messages=new ArrayList<>();

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
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email=email.replace("@","at").replace(".","dot");
        String schoolKey= getIntent().getStringExtra("schoolKey");
        PTF=database.getReference(getString(R.string.instance)).child("schools").child(schoolKey).child("parent-teacher-forum").child(email);
        PTF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    Messages msg = d1.getValue(Messages.class);
                    messages.add(msg);
                }
                pTFA=new ParentTeacherForumAdapter(messages);
                everything.setAdapter(pTFA);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageme.getText().toString().trim().length()>0)
                {
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy EEEE hh:mm a");
                    Messages msg=new Messages(messageme.getText().toString(),formatDate.format(Calendar.getInstance().getTime()),true);
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
}
