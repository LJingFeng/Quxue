package com.example.jinfeng.quxue.MyType;

import android.media.Image;
import android.widget.HorizontalScrollView;

/**
 * Created by JinFeng on 2017/8/7.
 */
public class HotSpot {
    String name;
    String teacher;
    String time;
    int ImageId;
    String imageUrl;
    public HotSpot(String name,String teacher,String time,String imageUrl){
        this.name=name;
        this.teacher=teacher;
        this.time=time;
        this.imageUrl=imageUrl;
    }
    public HotSpot(String name,String teacher,String time,int ImageId){
        this.name=name;
        this.time=time;
        this.teacher=teacher;
        this.ImageId=ImageId;
    }
    public String getName(){
        return name;
    }
    public String getTime(){
        return time;
    }
    public String getTeacher(){
        return time;
    }
    public int getImageId(){
        return ImageId;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}
