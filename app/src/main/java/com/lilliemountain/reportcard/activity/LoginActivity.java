package com.lilliemountain.reportcard.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {
    private  String TAG = LoginActivity.class.getSimpleName();
    EditText fieldEmail,fieldPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        fieldEmail=findViewById(R.id.fieldEmail);
        progressBar=findViewById(R.id.progressBar);
        fieldPassword=findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                findViewById(R.id.emailSignInButton).setClickable(false);
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

                                // ...
                            }
                        });
            }
        });
    }
    FirebaseUser currentUser;
    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        progressBar.setVisibility(View.VISIBLE);

        if(currentUser!=null)
        {
                            startActivity(new Intent(LoginActivity.this, UserActivity.class));

//            findViewById(R.id.emailSignInButton).setClickable(false);
//            database=FirebaseDatabase.getInstance();
//            instance=database.getReference(getString(R.string.instance));
//            admins=instance.child("committee");
//            admins.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    progressBar.setVisibility(View.GONE);
//
//                    for (DataSnapshot dataSnapshot1:
//                            dataSnapshot.getChildren()) {
////                        Committee committee=dataSnapshot1.getValue(Committee.class);
////
////
////                        if(committee.getEmail().toLowerCase().equals(currentUser.getEmail().toLowerCase())){
////                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
////                        }
////                        else {
////                            startActivity(new Intent(LoginActivity.this, UserActivity.class));
////                        }
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    progressBar.setVisibility(View.GONE);
//
//                }
//            });
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
