package com.example.jinfeng.quxue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.jinfeng.quxue.Adapter.AttentionAdapter;
import com.example.jinfeng.quxue.Adapter.HotSpotAdapter;
import com.example.jinfeng.quxue.Adapter.TopRecAdapter;
import com.example.jinfeng.quxue.DBconn.DBconnection;
import com.example.jinfeng.quxue.MyType.Classess;
import com.example.jinfeng.quxue.MyType.HotSpot;
import com.example.jinfeng.quxue.MyType.Teacher;
import com.example.jinfeng.quxue.MyType.TopRec;
import com.example.jinfeng.quxue.login.Login;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {
    RecyclerView topRec;
    RecyclerView yarecyvler;
    RecyclerView hotSpotRecycler;
    DrawerLayout mDrawerLayout;
    private List<HotSpot> hotSpotList = new ArrayList<>();
    private List<TopRec> mRecList = new ArrayList<>();
    private List<Teacher> mTeacherList =new ArrayList<>();
    private List<Classess> mClassList=new ArrayList<>();

    public static final int GET_DATA1 = 1;
    TopRecAdapter adapter;
    public static final int GET_DATA2 = 2;
    public static final int GET_DATA3 = 3;
    AttentionAdapter attentionAdapter;
    HotSpotAdapter hotSpotAdapter;
    BaseActivity baseActivity;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_DATA1:
                    mRecList = (List<TopRec>) msg.obj;
                    adapter = new TopRecAdapter(mRecList);
                    Log.d("SQL", "的长度是" + mRecList.size());
                    topRec.setAdapter(adapter);
                    break;
                case GET_DATA2:
                    mTeacherList=(List<Teacher>) msg.obj;
                    Log.d("SQL","Teacher"+mTeacherList.size()+"");
                    attentionAdapter=new AttentionAdapter(mTeacherList);
                    yarecyvler.setAdapter(attentionAdapter);
                    break;
                case GET_DATA3:
                    mClassList=(List<Classess>)msg.obj;
                    hotSpotAdapter=new HotSpotAdapter(mClassList);
                    hotSpotRecycler.setAdapter(hotSpotAdapter);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            collapsingToolbarLayout.setTitle("ll");
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        topRec = (RecyclerView) findViewById(R.id.rec_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topRec.setLayoutManager(layoutManager);
        initView();
        yarecyvler = (RecyclerView) findViewById(R.id.your_attention_recycler);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        yarecyvler.setLayoutManager(layoutManager2);
        yarecyvler.scrollBy(50, 90);

        hotSpotRecycler = (RecyclerView) findViewById(R.id.hot_spot_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        hotSpotRecycler.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);



        NavigationView navView = (NavigationView) findViewById(R.id.myNavigationview);
        View headerView = (View) navView.getHeaderView(0);
        CircleImageView headImage=(CircleImageView) headerView.findViewById(R.id.mainActivityHead);
        TextView nickName=(TextView)headerView.findViewById(R.id.nickname);

        Button login_out= (Button) headerView.findViewById(R.id.login_out);
        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences("data",MODE_PRIVATE);
                if(sharedPreferences!=null){
                    sharedPreferences.edit().clear().commit();
                }
                Intent intent=new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });



        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences!=null) {
            String headUrl = sharedPreferences.getString("headurl", "");
            if (headUrl.equals("")) {
                Intent  getIntent = getIntent();
                String telenumber=getIntent.getStringExtra("telephone");
                nickName.setText("手机用户"+telenumber);
            }else{
                String qqnickname = sharedPreferences.getString("nickname", "");
                Glide.with(this).load(headUrl).into(headImage);
                nickName.setText(qqnickname);
            }
        }


        Button beginCrowFunding = (Button) headerView.findViewById(R.id.begin_crowd_funding);

        beginCrowFunding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, select_course.class);
                startActivity(intent);
            }
        });
        ExpandableListView expandableListView = (ExpandableListView) navView.findViewById(R.id.myexpand_view);

        Button btn1 = (Button) headerView.findViewById(R.id.first_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "nihao", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Teacher> inmTeacherList = new ArrayList<Teacher>();
                try {
                    String sql = "select teacherid,job,t.teachername,u.headphoto ,stage,score,t.teacherDetail FROM teacher t inner join `user` u where t.userid=u.userid";
                    DBconnection dBconnection = new DBconnection();
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
                        Message msg2 = new Message();
                        msg2.obj = inmTeacherList;
                        msg2.what = GET_DATA2;
                        handler.sendMessage(msg2);
                    }
                    dBconnection.close();
                } catch (Exception e) {
                    Log.d("SQL", e.toString());
                }
            }
        }).start();


      /**  for (int i = 1; i < 10; i++) {
            HotSpot hot1 = new HotSpot("","name","time",R.drawable.appicon);
            hotSpotList.add(hot1);
        }
       */
       // 这里是原先调试使用的代码


        new Thread(new Runnable() {
            private List<TopRec> recListtemp = new ArrayList<>();
            @Override
            public void run() {// 这里开启一个子线程   该线程的功能是从远程数据库里面把数据提取出来 并且组装成本地的类，然后放到mReclist的表中
                Message msg3=new Message();
                List<Classess> inmClassList = new ArrayList<>();
                try {
                    String sql1 = "select * from course";
                    DBconnection con = new DBconnection();
                    ResultSet rs1 =null;
                    rs1= con.doQuery(sql1, "quxue");
                    while (rs1.next()) {
                        String courseid=rs1.getString(1);
                        String coursename = rs1.getString(2);
                        String cover=rs1.getString(3);
                        String detail=rs1.getString(4);
                        String teacher=""; // 这个数据暂时还没有老师这个选项所以暂时空着；
                        Classess class1=new Classess(courseid,coursename,cover,detail,teacher);
                        inmClassList.add(class1);


                        // 在这里的信息都可以正常显示，数据也已经成功的放到了mRecList表中，但是主线程里面的mRecList里面还是空的
                    }
                    msg3.what=GET_DATA3;
                    msg3.obj=inmClassList;
                    handler.sendMessage(msg3);
                    con.close();

                } catch (Exception e) {

                    Log.d("SQL", e.toString());
                }
            }
        }).start();
        new Thread(new Runnable() {
            private List<TopRec> recListtemp = new ArrayList<>();
            @Override
            public void run() {// 这里开启一个子线程   该线程的功能是从远程数据库里面把数据提取出来 并且组装成本地的类，然后放到mReclist的表中
                try {
                    String sql = "select * from course";
                    DBconnection con = new DBconnection();
                    ResultSet rs =null;
                    rs = con.doQuery(sql, "quxue");
                    Message msg3=new Message();
                    List<Classess> inmClassList = new ArrayList<>();
                    while (rs.next()) {
                        //
                        String id = rs.getString(2);
                        String imageUrl = rs.getString(3);
                        String name = rs.getString(1);
                        TopRec topRec = new TopRec(name, imageUrl, id);
                        Log.d("SQL",imageUrl);
                        recListtemp.add(topRec);
                        Message message=new Message();
                        Log.d("SQL",recListtemp.size()+"");
                        message.obj=recListtemp;
                        message.what=GET_DATA1;
                        handler.sendMessage(message);

                        // 在这里的信息都可以正常显示，数据也已经成功的放到了mRecList表中，但是主线程里面的mRecList里面还是空的
                    }
                    con.close();

                } catch (Exception e) {
                    Log.d("SQL", e.toString());
                }
            }
        }).start();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
            default:
                break;
        }
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}


