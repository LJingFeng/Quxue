package com.example.jinfeng.quxue.Adapter;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.MyType.Classess;
import com.example.jinfeng.quxue.MyType.HotSpot;
import com.example.jinfeng.quxue.R;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JinFeng on 2017/8/9.
 */
public class HotSpotAdapter extends RecyclerView.Adapter<HotSpotAdapter.ViewHolder>{
    private List<Classess> mHotSpotList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name;
        TextView teacher;
        public ViewHolder(View view){
            super(view);
            image=(CircleImageView) view.findViewById(R.id.hot_img);
            name=(TextView) view.findViewById(R.id.class_name);
            teacher=(TextView) view.findViewById(R.id.class_teacher);
        }
    }
    public HotSpotAdapter(List<Classess> mHotSpotList){
        this.mHotSpotList=mHotSpotList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
            Classess temp=mHotSpotList.get(position);
            Glide.with(mContext).load("http://"+temp.getCoverUrl())
                    .centerCrop()
                    .into(holder.image);
        holder.name.setText(temp.getCoursename());
        holder.teacher.setText(temp.getDetail());

       /* if(temp.getName().equals("")) {
            holder.name.setBackgroundColor(Color.parseColor("e"));
            holder.teacher.setBackgroundColor(Color.parseColor("#1F000000"));
        }
        else{
            holder.name.setBackgroundColor(Color.parseColor("#1F000000"));
            holder.teacher.setBackgroundColor(Color.parseColor("#1F000000"));
        }*/
    }
    @Override
    public int getItemCount(){
        return mHotSpotList.size();
    }

}
