package com.example.jinfeng.quxue.MyType;

/**
 * Created by JinFeng on 2017/8/6.
 */

public class TopRec {

    private String title;
    private int image;
    private String id;
    private String imageUrl;
    public TopRec(String title,int image){
        this.image=image;
        this.title=title;
    }
    public TopRec(String title,String imageUrl,String id){
        this.imageUrl=imageUrl;
        this.title=title;
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public int getImage(){
        return image;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getId(){
        return id;
    }
}
