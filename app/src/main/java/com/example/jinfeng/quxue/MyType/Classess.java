package com.example.jinfeng.quxue.MyType;

/**
 * Created by JinFeng on 2017/9/15.
 */

public class Classess {
    String courseid;
    String coursename;
    String coverUrl;
    String detail;
    String teacher;
    boolean ifSelected=false;
    public Classess(String courseid,String coursename,String coverUrl,String detail,String teacher){
        this.courseid=courseid;
        this.coursename=coursename;
        this.coverUrl=coverUrl;
        this.detail=detail;
        this.teacher=teacher;
    }
    public Classess(String courseid,String coursename,String coverUrl){
        this.coverUrl=coverUrl;
        this.coursename=coursename;
        this.courseid=courseid;
    }
    public String getCourseid(){
        return courseid;
    }
    public boolean getIfSelected(){
        return ifSelected;
    }
    public void setifSelected(boolean i){
        ifSelected=i;
    }
    public String getCoursename(){
        return coursename;
    }
    public String getDetail(){
        return detail;
    }
    public String getCoverUrl(){
        return coverUrl;
    }
    public String getTeacher(){
        return teacher;
    }
}
