package com.example.jinfeng.quxue.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.MyTitleLayout;
import com.example.jinfeng.quxue.MyType.Classess;
import com.example.jinfeng.quxue.MyType.CourseType;
import com.example.jinfeng.quxue.R;

import java.security.acl.Group;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JinFeng on 2017/9/7.
 */

public class SelectCourseAdapter extends RecyclerView.Adapter<SelectCourseAdapter.ViewHolder>{
    private List<Classess> mCourseTypeList;
    private Context mContext;
    private ViewGroup mParent;
    private RecyclerView.OnItemTouchListener mytemClickListener;
    MyTitleLayout myTitleLayout;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView CourseName;
        LinearLayout CourseSelect;
        ImageView ifChoose;
        LinearLayout linearLayout;
        public ViewHolder(View view){
            super(view);
           linearLayout = (LinearLayout)view.findViewById(R.id.select_course);
            imageView=(CircleImageView) view.findViewById(R.id.course_image);
            CourseName = (TextView) view.findViewById(R.id.course_name);
            CourseSelect = (LinearLayout) view.findViewById(R.id.select_course);
            ifChoose =(ImageView) view.findViewById(R.id.ifselected);
        }
    }
    public SelectCourseAdapter(List<Classess> CourseTypeList){
        mCourseTypeList=CourseTypeList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int typeView){
        mParent=parent;
            mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.course_select_item,parent,false);
        return new ViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int position){
        Classess temp=(Classess)mCourseTypeList.get(position);

        if(temp.getCoursename().equals("")){

        }else{
            viewHolder.CourseName.setText(temp.getCoursename());
            Glide
                    .with(mContext)
                    .load("http://"+temp.getCoverUrl())
                    .centerCrop()
                    .placeholder(R.drawable.appicon)
                    .into(viewHolder.imageView);
        }
        if(temp.getIfSelected()==false) {
            viewHolder.CourseName.setText(mCourseTypeList.get(position).getCoursename());
            viewHolder.ifChoose.setVisibility(View.INVISIBLE);



        }else{
            viewHolder.CourseName.setText(mCourseTypeList.get(position).getCoursename());
            viewHolder.ifChoose.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                viewHolder.linearLayout.setTranslationZ(120);
            }

        }
        viewHolder.CourseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp.getIfSelected()==false){
                    for(int i=0;i<mCourseTypeList.size();i++){
                        Classess courseType=(Classess) mCourseTypeList.get(i);
                        courseType.setifSelected(false);

                        notifyDataSetChanged();
                    }
                    temp.setifSelected(true);
                    notifyItemChanged(position);

                }else{
                    temp.setifSelected(false);
                    notifyItemChanged(position);
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return mCourseTypeList.size();
    }

    public void setItemClickListener(AdapterView.OnItemClickListener clickListener){
        this.mytemClickListener=mytemClickListener;
    }

}
