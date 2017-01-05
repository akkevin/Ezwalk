package com.example.user.ezwalk_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class Myprofile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                if(firebaseAuth.getCurrentUser()== null){

                    startActivity(new Intent(Myprofile.this, MainActivity.class));

                }
            }
        };

        Button mLogOutBtn = (Button) findViewById(R.id.Logout);

        mLogOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mAuth.signOut();
            }
        });
        Button Reivse=(Button)findViewById(R.id.revise);
        Reivse.setOnClickListener(reviselistener);
    }
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

    }
    View.OnClickListener reviselistener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(Myprofile.this,Data_revise.class));
        }
    };
}

