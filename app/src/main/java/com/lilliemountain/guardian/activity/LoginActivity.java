package com.lilliemountain.guardian.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lilliemountain.guardian.R;

public class LoginActivity extends AppCompatActivity {
    EditText fieldEmail,fieldPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        Intent exitIntent=getIntent();
        if(getIntent()!=null){
            if(exitIntent.hasExtra("EXIT")){
                if(exitIntent.getBooleanExtra("EXIT",false)){
                    super.finish();
                    System.exit(0);

                }
            }
        }

        MobileAds.initialize(this, getString(R.string.ad_mob_id));

        mAuth = FirebaseAuth.getInstance();
        fieldEmail=findViewById(R.id.fieldEmail);
        progressBar=findViewById(R.id.progressBar);
        fieldPassword=findViewById(R.id.fieldPassword);
        progressBar.setVisibility(View.GONE);

        findViewById(R.id.web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getString(R.string.website_url)));
                startActivity(i);
            }});
        findViewById(R.id.emailSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.emailSignInButton).setClickable(false);
                if (fieldEmail.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email cannot be left empty", Toast.LENGTH_SHORT).show();
                } else if (fieldPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password cannot be left empty", Toast.LENGTH_SHORT).show();
                } else if (fieldPassword.getText().toString().length() < 5) {
                    Toast.makeText(LoginActivity.this, "Password must have at least 5 characters", Toast.LENGTH_SHORT).show();
                }
                //place function here for email auth
                if (!(fieldEmail.getText().toString().isEmpty()) && !(fieldPassword.getText().toString().isEmpty()) && !(fieldPassword.getText().toString().length() <5)) {
                    login();
                }
            }
        });
    }
    void login(){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(fieldEmail.getText().toString(), fieldPassword.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            final FirebaseUser user = mAuth.getCurrentUser();
                            if(user!=null)
                            {
                                startActivity(new Intent(LoginActivity.this, UserActivity.class));
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            findViewById(R.id.emailSignInButton).setClickable(true);                                                progressBar.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
    FirebaseUser currentUser;
    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        progressBar.setVisibility(View.VISIBLE);
        String as=getIntent().getStringExtra("as");
        if (as=="as")
        {
            System.exit(0);
        }
        if(currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this, UserActivity.class));
        }
        else
            progressBar.setVisibility(View.GONE);

    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_how:
                showHelp();
                return true;
            case R.id.action_faq:
                Intent intesnt= new Intent(this,FAQActivity.class);
                startActivity(intesnt);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    int counter=1;

    private void showHelp() {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_help);
        final ConstraintLayout constraintLayout1,constraintLayout2,constraintLayout3;
        constraintLayout1=dialog.findViewById(R.id.constraintLayout1);
        constraintLayout2=dialog.findViewById(R.id.constraintLayout2);
        constraintLayout3=dialog.findViewById(R.id.constraintLayout3);
        final ProgressBar progressBar;
        progressBar=dialog.findViewById(R.id.progressBar2);
        progressBar.setMax(3);
        progressBar.setProgress(counter);
        dialog.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<=3){
                    switch (counter)
                    {
                        case 1:
                            constraintLayout1.setVisibility(View.VISIBLE);
                            constraintLayout2.setVisibility(View.GONE);
                            constraintLayout3.setVisibility(View.GONE);
                            counter++;
                            break;
                        case 2:
                            constraintLayout1.setVisibility(View.GONE);
                            constraintLayout2.setVisibility(View.VISIBLE);
                            constraintLayout3.setVisibility(View.GONE);
                            counter++;
                            break;
                        case 3:
                            constraintLayout1.setVisibility(View.GONE);
                            constraintLayout2.setVisibility(View.GONE);
                            constraintLayout3.setVisibility(View.VISIBLE);
                            counter++;
                            break;
                            default:
                                dialog.dismiss();
                                counter=0;
                    }
                }
                else{
                    counter=0;
                    dialog.dismiss();
                }
                progressBar.setProgress(counter);
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}
