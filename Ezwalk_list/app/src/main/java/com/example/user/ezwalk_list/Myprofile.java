package com.example.user.ezwalk_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Myprofile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String Mname;
    private String urll;
    private ImageView img;
    private String gender;
    private int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Mname = user.getDisplayName();
            urll = user.getPhotoUrl().toString();
            img=(ImageView)findViewById(R.id.pic);

            new AsyncTask<String,Void,Bitmap>()
            {
                @Override
                protected Bitmap doInBackground(String... params) {
                    String url=params[0];
                    return getBitmapFromURL(url);
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    img.setImageBitmap(bitmap);
                    super.onPostExecute(bitmap);
                }
            }.execute(urll);
        }

        Button mLogOutBtn = (Button) findViewById(R.id.Logout);

        mLogOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder=new AlertDialog.Builder(Myprofile.this);
                builder.setTitle("登出帳號")
                        .setMessage("你確定要登出此帳號？")
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mAuth.signOut();
                                    }
                                })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });
        Button Reivse=(Button)findViewById(R.id.revise);
        Reivse.setOnClickListener(reviselistener);
        loaddata();
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
    void loaddata(){
        Firebase myFirebaseRef = new Firebase("https://ezwalk-91e0d.firebaseio.com/users");
        Query queryRef=myFirebaseRef.orderByChild("name").equalTo(Mname);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user=dataSnapshot.getValue(User.class);
                gender=user.getGender();
                age=user.getAge();
                TextView name=(TextView)findViewById(R.id.name);
                name.setText(Mname+"\n"+gender+"  /  "+age+"歲");
                TextView interest=(TextView)findViewById(R.id.interest);
                interest.setText("興趣是："+user.getInterest());
                TextView saying=(TextView)findViewById(R.id.saying);
                saying.setText("我想說："+user.getSaying());
                TextView pertime=(TextView)findViewById(R.id.pertime);
                pertime.setText("每分鐘："+String.valueOf(user.getPertime())+"元");
                TextView perdistance=(TextView)findViewById(R.id.perdistance);
                perdistance.setText("每公尺："+String.valueOf(user.getPerdistance())+"元");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
    private static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Firebase myFirebaseRef = new Firebase("https://ezwalk-91e0d.firebaseio.com/users");
        Query queryRef=myFirebaseRef.orderByChild("name").equalTo(Mname);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user=dataSnapshot.getValue(User.class);
                gender=user.getGender();
                age=user.getAge();
                TextView name=(TextView)findViewById(R.id.name);
                name.setText(Mname+"\n"+gender+"  /  "+age+"歲");
                TextView interest=(TextView)findViewById(R.id.interest);
                interest.setText("興趣是："+user.getInterest());
                TextView saying=(TextView)findViewById(R.id.saying);
                saying.setText("我想說："+user.getSaying());
                TextView pertime=(TextView)findViewById(R.id.pertime);
                pertime.setText("每分鐘："+String.valueOf(user.getPertime())+"元");
                TextView perdistance=(TextView)findViewById(R.id.perdistance);
                perdistance.setText("每公尺："+String.valueOf(user.getPerdistance())+"元");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}

