package com.example.user.ezwalk_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Data_revise extends AppCompatActivity {
    private EditText Yearold;
    private EditText Interest;
    private EditText Saying;
    private EditText Pertime;
    private EditText Perdistance;
    private String name;
    private RadioGroup radioGroup;
    private RadioButton Male,Female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_revise);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://ezwalk-91e0d.firebaseio.com/");
        Yearold=(EditText)findViewById(R.id.edityearold);
        Interest=(EditText)findViewById(R.id.editinterest);
        Saying=(EditText)findViewById(R.id.editsaying);
        Pertime=(EditText)findViewById(R.id.editpertime);
        Perdistance=(EditText)findViewById(R.id.editperdistance);
        radioGroup=(RadioGroup) findViewById(R.id.gender);
        Male=(RadioButton)findViewById(R.id.male);
        Female=(RadioButton)findViewById(R.id.female);
        //initialize
        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        if(muser!=null){
            name = muser.getDisplayName();
        }
        final Firebase userRef = myFirebaseRef.child("users").child(name);
        Firebase iniRef=myFirebaseRef.child("users");
        iniRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    Yearold.setText(String.valueOf(user.getAge()));
                    Interest.setText(user.getInterest());
                    Saying.setText(user.getSaying());
                    Pertime.setText(String.valueOf(user.getPertime()));
                    Perdistance.setText(String.valueOf(user.getPerdistance()));
                    if(user.getGender()=="male")
                        Male.setChecked(true);
                    if(user.getGender()=="female")
                        Female.setChecked(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Button btn=(Button)findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yearold,pertime,perdistance;
                String interest,saying,Gender;
                Gender="";
                yearold=Integer.parseInt(Yearold.getText().toString());
                pertime=Integer.parseInt(Pertime.getText().toString());
                perdistance=Integer.parseInt(Perdistance.getText().toString());
                interest=Interest.getText().toString();
                saying=Saying.getText().toString();
                final User user=new User(yearold,Gender,interest,saying,pertime,perdistance);
                if(Male.isChecked())
                    user.setGender("male");
                if(Female.isChecked())
                    user.setGender("female");
                Gender=user.getGender();
                userRef.setValue(user, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(Data_revise.this,"revise error!",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Data_revise.this,"revise complete!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
            }
        });
    }


}
