package com.example.user.ezwalk_list;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.ezwalk_list.R;

import java.util.List;

/**
 * Created by user on 2016/12/22.
 */

public class ListAdapter extends BaseAdapter{
    public List<String> arraylist;
    private Context context;

    int count=10;
    public ListAdapter(Context context) {
        this.context = context;
    }

    public ListAdapter(Context context, List<String> lists) {
        this.context = context;
        arraylist = lists;
    }
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {return position;}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_mod, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

         viewHolder.itemTv.setText(arraylist.get(position));
        viewHolder.menuTv.setText("這是第 " + (position+1) + "個～");
        viewHolder.menuLy.measure(0, 0);
        final int height = viewHolder.menuLy.getMeasuredHeight();
        viewHolder.itemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.menuLy.getVisibility() == View.GONE){
                    show(viewHolder.menuLy,height);
                }else{
                    dismiss(viewHolder.menuLy,height);
                }
            }
        });
        return convertView;
    }

    public void show(final View v,int height)//向下動畫
    {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator=ValueAnimator.ofInt(0,height);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value=(Integer)animation.getAnimatedValue();
                v.getLayoutParams().height=value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }
    public  void dismiss(final View v,int height)//向上動畫
    {
        ValueAnimator animator = ValueAnimator.ofInt(height,0);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                if (value == 0) {
                    v.setVisibility(View.GONE);
                }
                v.getLayoutParams().height = value;
                v.setLayoutParams(v.getLayoutParams());
            }
        });
        animator.start();
    }
    class ViewHolder {
        View rootView;
        TextView itemTv;
        TextView menuTv;
        FrameLayout menuLy;

        public ViewHolder(View view) {
            rootView = view;
            itemTv = (TextView) view.findViewById(R.id.item_layout);
            menuTv = (TextView) view.findViewById(R.id.menu_tv);
            menuLy = (FrameLayout) view.findViewById(R.id.menu_layout);
        }
    }
}
