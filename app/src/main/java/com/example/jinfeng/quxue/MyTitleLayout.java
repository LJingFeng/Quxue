package com.example.jinfeng.quxue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JinFeng on 2017/8/22.
 */

public class MyTitleLayout extends LinearLayout {
    Button myBack;
    Button myNext;
    TextView myTitle;
    public MyTitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.mytoolbar_layout,this);
        myTitle= (TextView) findViewById(R.id.my_tool_bar_title);
        myBack = (Button) findViewById(R.id.my_tool_bar_back);
        myNext =(Button) findViewById(R.id.my_tool_bar_next);
        myBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();      // 结束当前活动
            }
        });
        myNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {    //

            }
        });
    }
    public void setTitle(String title){
        myTitle.setText("");
        myTitle.setText("已选择"+"{"+title+"}");
    }
    public void setTitle1(String title){
        myTitle.setText("");
        myTitle.setTextColor(Color.parseColor("#FFFFFFFF"));
        myTitle.setText(title);
    }
    public void setButtonEnable(boolean i){
        myNext.setEnabled(i);
        myNext.setTextColor(Color.parseColor("#FF1867FF"));
    }
}
