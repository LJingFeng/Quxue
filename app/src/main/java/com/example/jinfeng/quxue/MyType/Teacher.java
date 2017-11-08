package com.example.jinfeng.quxue.MyType;

/**
 * Created by JinFeng on 2017/8/22.
 */

public class Teacher {
    private String name;
    private String  teacherId;
    private String teacherHeadUrl;
    private String teacherDetail;
    private String teacherScore;
    private boolean isCheck=false;

    public Teacher(String name,String teacherId,String  teacherDetail,String score){
        this.name=name;
        this.teacherId=teacherId;
        this.teacherScore=score;
        this.teacherDetail=teacherDetail;
    }

    public Teacher(String name,String teacherId,String teacherHeadUrl,String teacherDetail,String teacherScore){
        this.name=name;
        this.teacherDetail=teacherDetail;
        this.teacherHeadUrl=teacherHeadUrl;
        this.teacherId=teacherId;
        this.teacherScore=teacherScore;
    }
    public String getTeacherHeadUrl() {
        return teacherHeadUrl;
    }
    public String getTeacherScore(){
        return teacherScore;
    }
    public String getTeacherDetail(){
        return teacherDetail;
    }
    public String getName(){
        return name;
    }
    public boolean getIsChecked(){
        return isCheck;
    }
    public void setIsChecked(boolean isCheck){
        this.isCheck=isCheck;
    }
    public String getTeacherId(){
        return teacherId;
    }
}
