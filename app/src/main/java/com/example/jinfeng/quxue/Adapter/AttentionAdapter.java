package com.example.jinfeng.quxue.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.MyType.HotSpot;
import com.example.jinfeng.quxue.MyType.Teacher;
import com.example.jinfeng.quxue.R;

import java.util.List;

/**
 * Created by JinFeng on 2017/8/7.
 */

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.ViewHolder> {
    private Context mContext;
    private List<Teacher> teacherList;

    public AttentionAdapter(List<Teacher> mHotList) {
        if(mHotList.size()<=0){
            Teacher teacher1=new Teacher("1","","","");
            Teacher teacher2=new Teacher("1","","","");
            Teacher teacher3=new Teacher("1","","","");
            mHotList.add(teacher1);
            mHotList.add(teacher2);
            mHotList.add(teacher3);
            this.teacherList = mHotList;
        }
        if(mHotList.size()>0){
            this.teacherList = mHotList;
        }
    }

    @Override
    public AttentionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.attention_item, parent, false);
        return new AttentionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Teacher teacher= teacherList.get(position);
        /**
         *  这里做加载图片的操作
         *
         */
        if (teacher.getName().equals("")) {
            if(position==0){
                holder.attentionLayout.setPadding(16,0,0,0);
            }
            if(teacher.getName()=="") {

                holder.attentionRec.setBackgroundColor(Color.parseColor("#1F000000"));
                holder.attentionTec.setBackgroundColor(Color.parseColor("#1F000000"));
                holder.attentionTime.setBackgroundColor(Color.parseColor("#1F000000"));
            }
        }
        else{
            if(position==0){
                holder.attentionLayout.setPadding(16,0,0,0);
            }
            Glide.with(mContext).load("http://"+teacher.getTeacherHeadUrl()).centerCrop().into(holder.attentionImg);

            holder.attentionRec.setText(teacher.getTeacherDetail());
            holder.attentionTec.setText(teacher.getName());
            holder.attentionTime.setText(teacher.getTeacherScore());
        }
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView attentionImg;
        TextView attentionRec;
        TextView attentionTec;
        TextView attentionTime;
        LinearLayout attentionLayout;

        public ViewHolder(View view) {
            super(view);
            attentionLayout =(LinearLayout) view.findViewById(R.id.attention_layout);
            attentionImg = (ImageView) view.findViewById(R.id.attention_img);
            attentionRec = (TextView) view.findViewById(R.id.attention_rec);
            attentionTec = (TextView) view.findViewById(R.id.attention_tec);
            attentionTime = (TextView) view.findViewById(R.id.attention_time);
        }
    }
}
