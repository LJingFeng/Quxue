package com.example.jinfeng.quxue;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinfeng.quxue.Adapter.SelectTeacherAdapter;
import com.example.jinfeng.quxue.DBconn.DBconnection;
import com.example.jinfeng.quxue.MyType.Teacher;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectTeacher extends BaseActivity {
    SelectTeacherAdapter teacherAdapter;
    private List<Teacher> mList=new ArrayList<>();
    RecyclerView recyclerView;
    private final int GET_DATA4=4;


    Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_DATA4:
                    mList=(List<Teacher>)msg.obj;
                    teacherAdapter=new SelectTeacherAdapter(mList);
                    recyclerView.setAdapter(teacherAdapter);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_teacher);
        Button toolbarBack = (Button) findViewById(R.id.my_tool_bar_back);
        TextView myTextView = (TextView) findViewById(R.id.my_tool_bar_title);
        myTextView.setText("请选择");
        initViewList();
        MyTitleLayout titeLayout=(MyTitleLayout) findViewById(R.id.teacher_titleLayout) ;
        Button next=(Button)titeLayout.findViewById(R.id.my_tool_bar_next);
        Button back=(Button)titeLayout.findViewById(R.id.my_tool_bar_back);

        LayoutInflater inflater=getLayoutInflater();
        View view1=inflater.inflate(R.layout.teacher_select_frame1,null);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        String className = intent.getStringExtra("classname");
        String classImageUrl = intent.getStringExtra("classImageUrl");
        String classId= intent.getStringExtra("classid");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher temp;
                for(int i=0;i<mList.size();i++)
                {
                   temp=(Teacher) mList.get(i);
                    if(temp.getIsChecked()){
                        Log.d("TT", temp.getName()+temp.getIsChecked()+temp.getTeacherDetail());
                        Intent intent1=new Intent(SelectTeacher.this,Complete.class);
                        intent1.putExtra("teacherUrl",temp.getTeacherHeadUrl());
                        intent1.putExtra("teacherid",temp.getTeacherId());
                        intent1.putExtra("teachername",temp.getName());

                        intent1.putExtra("classname",className);
                        intent1.putExtra("classid",classId);
                        intent1.putExtra("classImageUrl",classImageUrl);// 在这可以传递书或者把对象变为序列化对象就可以传递对象
                        startActivity(intent1);
                        break;                  // 在这里获取用户已选中的对象 并且吧把对象传递给下一个活动
                        // 并且获取上一个活动传来的教师信息 把信息都传递给下一个活动  完成众筹过程
                    }
                    if(i==mList.size()-1){
                        Toast.makeText(SelectTeacher.this, "请选择您的老师", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView=(RecyclerView) findViewById(R.id.teacher_recyclerview);
        recyclerView.setLayoutManager(manager);
    }
    public void initViewList(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Teacher> inmTeacherList = new ArrayList<Teacher>();
                DBconnection dBconnection = new DBconnection();
                Message msg2 = new Message();
                try {
                    String sql = "select teacherid,job,t.teachername,u.headphoto ,stage,score,t.teacherDetail FROM teacher t inner join `user` u where t.userid=u.userid";

                    ResultSet rs = null;
                    rs = dBconnection.doQuery(sql, "quxue");
                    while (rs.next()) {
                        String teacherId = rs.getString(1);
                        String name = rs.getString(3);
                        String stage = rs.getString(5);
                        String score = rs.getString(6);
                        String teacherDetail = rs.getString(7);
                        String imageUrl = rs.getString(4);
                        Log.d("SQL", name + score);
                        Teacher teacher = new Teacher(name, teacherId, imageUrl, teacherDetail, score);
                        inmTeacherList.add(teacher);
                        msg2.obj = inmTeacherList;
                        msg2.what = GET_DATA4;

                    }
                    handler.sendMessage(msg2);
                    dBconnection.close();
                } catch (Exception e) {
                    Log.d("SQL", e.toString());
                }
            }
        }).start();
        }
        public void setTitle(){

        }

}
