package com.lilliemountain.reportcard.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;

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
                    String schoolName;
                    DataSnapshot students;
                    schoolName=d1.child("schoolName").getValue().toString();

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
                welcomeuser.setText(getString(R.string.welcome_guardian_of)+of);
                if(children.size()==1)
                checkoutchild.setText(getString(R.string.checkchild));
                else
                checkoutchild.setText(getString(R.string.checkchildren));
                childAdapter=new ChildAdapter(children,schoolist,UserActivity.this);
                kidslist.setAdapter(childAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }
    private void showLanguageChangePopup() {
        CharSequence languages[] = new CharSequence[] {
                "English",
                "हिंदी (Hindi)",
                "मराठी (Marathi)"
        };
        final String codes[] = new String[] {
                "en",
                "hi",
                "mr"
        };
        ReportCardManager.initializeInstance(this);
        int currentLangIndex = 0 ;
        try {
            currentLangIndex = Integer.parseInt(ReportCardManager.getInstance().getValue("lang"));
        } catch (NumberFormatException e) {
            currentLangIndex = 0 ;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.text_select_language);
        builder.setSingleChoiceItems(languages, currentLangIndex, null);
        builder.setNegativeButton(R.string.text_translate_cancel, null);
        builder.setPositiveButton(R.string.action_change_language, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int selectedIndex = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                ReportCardManager.getInstance().setValue("lang", String.valueOf(selectedIndex));
                setLanguageForApp(codes[selectedIndex]);
                startActivity(new Intent(UserActivity.this,UserActivity.class));
                dialog.dismiss();
            }
        });

        builder.show();
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_translate:
                showLanguageChangePopup();
                return true;
            case R.id.action_signOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(this,LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent= new Intent(this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT",true);
            startActivity(intent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.pleaseclick), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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
    private void setLanguageForApp(String languageToLoad){
        Locale locale;
        if(languageToLoad.equals("not-set")){ //use any value for default
            locale = Locale.getDefault();
        }
        else {
            locale = new Locale(languageToLoad);
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}