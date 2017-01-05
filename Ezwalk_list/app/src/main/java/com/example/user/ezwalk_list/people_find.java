package com.example.user.ezwalk_list;

import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class people_find extends ListActivity
implements AbsListView.OnScrollListener{
    private ListView lsv_main;
    private ListAdapter mAdapter;

    private int mLastItem=0;
    private int mCount=3000;
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
        List<String> itemList=new ArrayList<String>();
        for(int i=0;i<mCount;i++){
            itemList.add("No."+i);
        }
        mAdapter=new ListAdapter(people_find.this,itemList);
        mLoadLayout=new LinearLayout(this);
        mLoadLayout.setMinimumHeight(60);
        mLoadLayout.setGravity(Gravity.CENTER);
        mLoadLayout.setOrientation(LinearLayout.HORIZONTAL);

        ProgressBar mProgressBar = new ProgressBar(this);
        mProgressBar.setPadding(0, 0, 15, 0);
        mLoadLayout.addView(mProgressBar, mProgressBarLayoutParams);

        TextView mTipContent = new TextView(this);
        mTipContent.setText("加載中......");
        mLoadLayout.addView(mTipContent, mTipContentLayoutParams);
        lsv_main=getListView();
        lsv_main.addFooterView(mLoadLayout);

        setListAdapter(mAdapter);
        lsv_main.setOnScrollListener(this);
        lsv_main.setOnItemClickListener(listViewOnItemClickListener);
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
                        mAdapter.count+=30;
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
            Toast.makeText(people_find.this,"需要"+10*(position+1)+"元喔！",Toast.LENGTH_SHORT).show();
        }
    };
}
