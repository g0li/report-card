package com.lilliemountain.reportcard.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lilliemountain.reportcard.R;
import com.lilliemountain.reportcard.ReportCardManager;

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


        mAuth = FirebaseAuth.getInstance();
        fieldEmail=findViewById(R.id.fieldEmail);
        progressBar=findViewById(R.id.progressBar);
        fieldPassword=findViewById(R.id.fieldPassword);
        progressBar.setVisibility(View.GONE);

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
}
