package com.example.jinfeng.quxue.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.media.Rating;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.MyTitleLayout;
import com.example.jinfeng.quxue.MyType.Teacher;
import com.example.jinfeng.quxue.R;
import com.example.jinfeng.quxue.SelectTeacher;

import org.w3c.dom.Text;

import java.util.List;

import cn.smssdk.gui.layout.TitleLayout;

/**
 * Created by JinFeng on 2017/8/22.
 */

public class SelectTeacherAdapter extends RecyclerView.Adapter<SelectTeacherAdapter.ViewHolder> {
    private Context context;
    private List<Teacher> mTeacherList;
    MyTitleLayout titleLayout;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mycardView;
        ImageView teacherHeader;
        TextView teacherTDetail;
        RatingBar teacherLevle;
        TextView teacherScore;
        ImageView checkImage;
        RatingBar ratingBar;
        public ViewHolder(View view){
            super(view);
            checkImage = (ImageView)view.findViewById(R.id.check_img);
            mycardView = (CardView) view.findViewById(R.id.card) ;
            teacherHeader = (ImageView) view.findViewById(R.id.teacher_header);
            teacherTDetail =(TextView) view.findViewById(R.id.teacer_detail);
            teacherLevle =(RatingBar) view.findViewById(R.id.teahcer_star_level);
            teacherScore=(TextView)view.findViewById(R.id.teacher_score);
            ratingBar = (RatingBar)view.findViewById(R.id.teahcer_star_level);
        }
    }
    public SelectTeacherAdapter(List<Teacher> TeacherList) {
        mTeacherList =TeacherList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int typeView){
        context=parent.getContext();
         titleLayout=(MyTitleLayout) parent.findViewById(R.id.teacher_titleLayout);
        View view= LayoutInflater.from(context).inflate(R.layout.teacher_selected_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Teacher teacher=mTeacherList.get(position);
        String teacherDetail = teacher.getTeacherDetail();
        String teacherUrl = teacher.getTeacherHeadUrl();
        String teacherDScore = teacher.getTeacherScore();
     float  score=  Float.parseFloat(teacherDScore);

        if(teacher.getName().equals("name")){
            holder.teacherHeader.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }
        else{
            Glide.with(context).load("http://"+teacher.getTeacherHeadUrl())
                    .centerCrop()
                    .into(holder.teacherHeader);
            holder.teacherScore.setText(""+score);
            holder.teacherTDetail.setText(teacher.getTeacherDetail());
            holder.ratingBar.setRating(score);
            if(teacher.getIsChecked()){
                holder.checkImage.setVisibility(View.VISIBLE);
                holder.checkImage.bringToFront();
                holder.teacherScore.setText(""+score);
                holder.ratingBar.setRating(score);
            }else{
                holder.checkImage.setVisibility(View.INVISIBLE);
            }
        }
        holder.mycardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teacher.getIsChecked()){

                    teacher.setIsChecked(false);
                    notifyItemChanged(position);
                }else{
                    for(int i=0;i<mTeacherList.size();i++){
                        Teacher temp=mTeacherList.get(i);
                        temp.setIsChecked(false);
                        notifyDataSetChanged();
                    }
                    teacher.setIsChecked(true);
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return mTeacherList.size();
    }
}