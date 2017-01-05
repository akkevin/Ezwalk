package com.example.user.ezwalk_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainSurround extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_surround);
        Button people=(Button)findViewById(R.id.walkgo);
        Button myprofile=(Button)findViewById(R.id.profile);
        Button chatroom=(Button)findViewById(R.id.chat);
       people.setOnClickListener(peoplelistener);
        myprofile.setOnClickListener(myprofilelistener);
        chatroom.setOnClickListener(chatroomlistener);
    }

    View.OnClickListener peoplelistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainSurround.this,people_find.class));
        }
    };
    View.OnClickListener myprofilelistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainSurround.this,Myprofile.class));
        }
    };
    View.OnClickListener chatroomlistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainSurround.this,chatroom.class));
        }
    };

}
