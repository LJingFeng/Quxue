package com.example.jinfeng.quxue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.jinfeng.quxue.Adapter.HotSpotAdapter;
import com.example.jinfeng.quxue.Adapter.SelectCourseAdapter;
import com.example.jinfeng.quxue.DBconn.DBconnection;
import com.example.jinfeng.quxue.MyType.Classess;
import com.example.jinfeng.quxue.MyType.CourseType;
import com.example.jinfeng.quxue.MyType.HotSpot;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class select_course extends AppCompatActivity {
    private List<Classess> mList=new ArrayList<>();
    private RecyclerView courseSelectrecy;
    private RecyclerView recomandTeacher;
    Button next;
    Button back;
    SelectCourseAdapter adapter;
    private final int  GET_CLSSSES_DATA=1;
    Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case GET_CLSSSES_DATA:
                    mList=(List<Classess>) msg.obj;
                    adapter =new SelectCourseAdapter(mList);
                    HotSpotAdapter adapter2= new HotSpotAdapter(mList);
                    courseSelectrecy.setAdapter(adapter);
                    recomandTeacher.setAdapter(adapter2);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        initView();
        recomandTeacher = (RecyclerView) findViewById(R.id.teacher_recomand);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        recomandTeacher.setLayoutManager(manager2);

        courseSelectrecy = (RecyclerView) findViewById(R.id.course_items);
        MyTitleLayout titleLayout=(MyTitleLayout) findViewById(R.id.course_titlelayout);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseSelectrecy.setLayoutManager(manager);

        courseSelectrecy.setAdapter(adapter);
        next = (Button) titleLayout.findViewById(R.id.my_tool_bar_next);

        back = (Button) titleLayout.findViewById(R.id.my_tool_bar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();//在这里销毁当前的活动
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<mList.size();i++) {
                    Classess type=(Classess) mList.get(i);
                    if(type.getIfSelected()) {
                        String className=type.getCoursename();
                        String classId=type.getCourseid();
                        String classImageUrl=type.getCoverUrl();
                        Intent toSelectTeahcer = new Intent(select_course.this, SelectTeacher.class);
                        toSelectTeahcer.putExtra("classname",className);
                        toSelectTeahcer.putExtra("classid",classId);
                        toSelectTeahcer.putExtra("classImageUrl",classImageUrl);
                        startActivity(toSelectTeahcer);  //下一步跳转至选择老师的界面  并且携带当前的信息
                    }
                    if(i==mList.size()){
                        Toast.makeText(select_course.this, "请选择课程", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
    public void initView(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg1=new Message();
                List<Classess> inmClassesList=new ArrayList<>();
                try {
                    DBconnection dBconnection = new DBconnection();
                    String ql = "select * from course";
                    ResultSet rs=null;
                    rs=dBconnection.doQuery(ql,"quxue");
                    while (rs.next()) {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        Log.d("SQL",s1+s2);
                        String s3=rs.getString(3);
                        Classess classess=new Classess(s1,s2,s3);
                        inmClassesList.add(classess);
                        msg1.obj=inmClassesList;
                        msg1.what=GET_CLSSSES_DATA;
                    }
                    handler.sendMessage(msg1);
                    dBconnection.close();

                }catch(Exception e){
                    Log.d("sql",e.toString());
                }
            }
        }).start();
    }
}
