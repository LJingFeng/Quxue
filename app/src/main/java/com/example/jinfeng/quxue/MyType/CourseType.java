package com.example.jinfeng.quxue.MyType;

/**
 * Created by JinFeng on 2017/9/7.
 */

public class CourseType {
    String name;
    int id;
    String imageUrl;
    int imageid;
    boolean ifSelected;

    public CourseType(String name,int id,String imageUrl){
        this.name=name;
        this.id=id;
        this.imageUrl=imageUrl;
    }
    public CourseType(String name,int id,int imageid){
        this.name=name;
        this.imageid=imageid;
        this.id=id;
    }

    public String getName() {
        return name;
    }
    public String getImageUrl(){
        return  imageUrl;
    }
    public int getImageid(){
        return imageid;
    }
    public boolean isIfSelected(){
        return  ifSelected;
    }
    public void setIfChoose(boolean i){
        ifSelected=i;
    }
}
