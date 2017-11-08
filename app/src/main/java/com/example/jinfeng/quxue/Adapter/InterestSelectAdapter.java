package com.example.jinfeng.quxue.Adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.example.jinfeng.quxue.MyType.MyInterest;
import com.example.jinfeng.quxue.R;
import java.util.List;
import static com.tencent.open.utils.Global.getContext;

/**
 * Created by JinFeng on 2017/8/5.
 */

public class InterestSelectAdapter extends RecyclerView.Adapter<InterestSelectAdapter.ViewHolder>{
    private List<MyInterest> mInterestList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;

        public ViewHolder(View view){
            super(view);
            checkBox=(CheckBox) view.findViewById(R.id.interset_check_box_item);
        }
    }
    public InterestSelectAdapter(List<MyInterest> mInterestList){
        this.mInterestList=mInterestList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view =LayoutInflater.from(getContext()).inflate(R.layout.checkbox_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    viewHolder.checkBox.setTextColor(Color.parseColor("#2A88CB"));
                }
                else{
                    viewHolder.checkBox.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
            }
        });
        return  viewHolder;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        MyInterest tempIn = mInterestList.get(position);
        holder.checkBox.setText(tempIn.getName());
    }
    @Override
    public int getItemCount(){
        return mInterestList.size();
    }

}
