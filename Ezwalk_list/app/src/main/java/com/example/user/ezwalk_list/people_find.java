package com.example.user.ezwalk_list;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class people_find extends ListActivity
implements AbsListView.OnScrollListener{
    private ListView lsv_main;
    private ListAdapter mAdapter;
    private int mLastItem=0;
    int mCount=1000;
    int CCount=1;
    final List<String> itemList=new ArrayList<String>();
    private LinearLayout mLoadLayout;
    private final Handler mHandler=new Handler();
    private final LinearLayout.LayoutParams mProgressBarLayoutParams=new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    private final LinearLayout.LayoutParams mTipContentLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_find);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://ezwalk-91e0d.firebaseio.com/users");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CCount=(int)dataSnapshot.getChildrenCount();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                    itemList.add(snapshot.child("name").getValue().toString());
                }
                for(int i=CCount;i<30;i++){
                    itemList.add("NO DATA");
                }
                mAdapter=new ListAdapter(people_find.this,itemList);
                mLoadLayout=new LinearLayout(people_find.this);
                mLoadLayout.setMinimumHeight(60);
                mLoadLayout.setGravity(Gravity.CENTER);
                mLoadLayout.setOrientation(LinearLayout.HORIZONTAL);

                ProgressBar mProgressBar = new ProgressBar(people_find.this);
                mProgressBar.setPadding(0, 0, 15, 0);
                mLoadLayout.addView(mProgressBar, mProgressBarLayoutParams);

                TextView mTipContent = new TextView(people_find.this);
                mTipContent.setText("加載中......");
                mLoadLayout.addView(mTipContent, mTipContentLayoutParams);
                lsv_main=getListView();
                lsv_main.addFooterView(mLoadLayout);

                setListAdapter(mAdapter);
                lsv_main.setOnScrollListener(people_find.this);
                lsv_main.setOnItemClickListener(listViewOnItemClickListener);
                lsv_main.setOnItemLongClickListener(listViewOnItemLongClickListener);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mLastItem=firstVisibleItem+visibleItemCount-1;
        if(mAdapter.count>mCount)
            lsv_main.removeFooterView(mLoadLayout);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(mLastItem==mAdapter.count
                && scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
        {
            if(mAdapter.count<=mCount)
            {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.count+=10;
                        mAdapter.notifyDataSetChanged();
                        lsv_main.setSelection(mLastItem);
                    }
                },1000);
            }
        }
    }
    private AdapterView.OnItemClickListener listViewOnItemClickListener
            =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

        }
    };
    private AdapterView.OnItemLongClickListener listViewOnItemLongClickListener
            =new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder builder=new AlertDialog.Builder(people_find.this);
            builder.setTitle("邀請")
                    .setMessage("你確定要開始跟他/她聊天？")
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(people_find.this,"hahahahaha",Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setNegativeButton("取消",null)
                    .show();
            return false;
        }
    };
}
