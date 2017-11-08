package com.example.jinfeng.quxue.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.DBconn.DBconnection;
import com.example.jinfeng.quxue.MyType.TopRec;
import com.example.jinfeng.quxue.R;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by JinFeng on 2017/8/6.
 */

public class TopRecAdapter  extends RecyclerView.Adapter<TopRecAdapter.ViewHolder>{
    private Context mContext;
    private List<TopRec> mRecList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView recImage;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view.findViewById(R.id.top_rec_card);
            recImage =(ImageView) view.findViewById(R.id.top_rec_img);
        }
    }
    public TopRecAdapter(List<TopRec> mRecList){

        this.mRecList=mRecList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.top_rec_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        TopRec topRec = mRecList.get(position);
        if (topRec.getImageUrl().equals("")) {
        } else {
            Glide
                    .with(mContext)
                    .load("http://"+topRec.getImageUrl())// 在这里还是有错误 添加这段语句之后程序就会Crush
                    .centerCrop()
                    .into(holder.recImage);
        }
    }

    @Override
    public int getItemCount(){
        return mRecList.size();
    }
}
