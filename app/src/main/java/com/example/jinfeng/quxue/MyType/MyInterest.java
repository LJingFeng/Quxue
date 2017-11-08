package com.example.jinfeng.quxue.MyType;

/**
 * Created by JinFeng on 2017/8/5.
 */

public class MyInterest {
    private String name;
    private int myInterestId;
    private String type;
    public MyInterest(String name,String type,int myInterestId){
        this.myInterestId=myInterestId;
        this.name=name;
        this.type=type;
    }
    public String getName(){
        return name;
    }
    public String getType() {
        return type;
    }
}
