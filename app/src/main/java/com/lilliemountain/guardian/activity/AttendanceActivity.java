package com.lilliemountain.guardian.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.guardian.R;
import com.lilliemountain.guardian.model.Attendance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AttendanceActivity extends AppCompatActivity {
    String rollno,grade,name;
    String schoolKey;
    FirebaseDatabase database;
    DatabaseReference instance,attendance;
    List<Attendance> attendanceList=new ArrayList<>();
    List<String> listofmonths=new ArrayList<>();
    TextView attendance2,attendanceTV,ratio,percentage,datetv;
    HashMap<String,List<Attendance>> stringListHashMap=new HashMap<>();
    Spinner my,d;
    ArrayAdapter<String> myArrayAdapter,dArrayAdapter;
    List<String> myStringList=new ArrayList<>();
    List<Attendance> datesList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_attendance);
        attendance2=findViewById(R.id.attendance2);
        attendanceTV=findViewById(R.id.attendance);
        ratio=findViewById(R.id.ratio);
        percentage=findViewById(R.id.percentage);
        datetv=findViewById(R.id.date);
        my=findViewById(R.id.my);
        d=findViewById(R.id.d);

        rollno=getIntent().getStringExtra("rollno");
        grade=getIntent().getStringExtra("grade");
        name=getIntent().getStringExtra("name");
        getSupportActionBar().setTitle("Attendance");
        getSupportActionBar().setSubtitle("Roll no :" + rollno);

        schoolKey=getIntent().getStringExtra("schoolKey");
        database=FirebaseDatabase.getInstance();
        instance=database.getReference(getString(R.string.instance));
        attendance=instance.child("schools").child(schoolKey);
        attendance.child("attendance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listofmonths.clear();
                attendanceList.clear();
                stringListHashMap.clear();
                myStringList.clear();
                datesList.clear();
                for (DataSnapshot d1 :
                        dataSnapshot.getChildren()) {
                    listofmonths.add(d1.getKey());
                    for (DataSnapshot d2 :
                            d1.getChildren()) {
                        Log.e( "d2.getKey(): ",d2.getKey() );
                        Attendance a=d2.getValue(Attendance.class);
                        if(a.getRollNo().equals(rollno) && a.getChildGrade().equals(grade) && a.getChildName().equals(name))
                        attendanceList.add(a);
                    }
                    Log.e( "d2.getKey(): ",d1.getKey() );
                    stringListHashMap.put(d1.getKey(),attendanceList);
                }
                Set<String> temString=stringListHashMap.keySet();
                for (String s :
                        temString) {
                    myStringList.add(s);
                }
                myArrayAdapter=new ArrayAdapter<>(AttendanceActivity.this,R.layout.item_spinner,myStringList);
                my.setAdapter(myArrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        my.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key=myStringList.get(position);
                datesList.clear();
                datesList.addAll(stringListHashMap.get(key));
                final List<String>dates=new ArrayList<>();
                for (Attendance a :
                        datesList) {
                    dates.add(a.getDateOfAttendance());
                }
                dArrayAdapter = new ArrayAdapter<String>(
                        AttendanceActivity.this,R.layout.item_spinner,dates){
                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (datesList.get(position).getAttendance().toLowerCase().equals("absent")) {
                            tv.setTextColor(Color.parseColor("#B22222"));
                        }
                        else {
                            tv.setTextColor(Color.parseColor("#000000"));
                        }
                        return view;
                    }
                };
                d.setAdapter(dArrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String date= new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                if(date.equals(datesList.get(position).getDateOfAttendance()))
                    datetv.setText(getString(R.string.today));
                else
                    datetv.setText(datesList.get(position).getDateOfAttendance());
                attendance2.setText(datesList.get(position).getAttendance());
                String das=datesList.get(position).getAttendance().toUpperCase();
                if(das.charAt(0)==('P'))
                {
                    attendance2.setTextColor(Color.parseColor("#ff669900"));
                    attendanceTV.setTextColor(Color.parseColor("#ff669900"));
                }
                else{
                    attendance2.setTextColor(Color.parseColor("#ffcc0000"));
                    attendanceTV.setTextColor(Color.parseColor("#ffcc0000"));
                }
                attendanceTV.setText(das.substring(0,1));
                Double present=0.0;
                for (int i = 0; i < datesList.size(); i++) {
                    if(datesList.get(i).getAttendance().equals("present"))
                        present++;
                }
                String s=getString(R.string.total_att)+" "+present.intValue()+"/"+datesList.size();
                ratio.setText(s);
                Double perc=present/datesList.size()*100;
                percentage.setText((getString(R.string.att_of)+" " + my.getSelectedItem().toString() + " - " +perc+" %."));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
