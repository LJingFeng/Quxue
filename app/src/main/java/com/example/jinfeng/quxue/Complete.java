package com.example.jinfeng.quxue;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.jinfeng.quxue.DBconn.DBconnection;

import java.sql.Array;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Complete extends AppCompatActivity implements View.OnClickListener{
    private Button chooseTimeLong;
    private  Dialog mDialog;
    private TextView teacherName;
    private  TextView className;
    private EditText classTitle;
    private Button classTimeLong;
    private  RadioGroup radioGroup;
    private EditText crowdMoney;
    private EditText crowTime;
    private EditText crowNumber;
    private ScrollView completeScrowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_complete);
        MyTitleLayout completeTitleLyaout=(MyTitleLayout) findViewById(R.id.complete_titlelayout);
        completeTitleLyaout.setTitle1("请完善课程信息");


        /**
         *   Intent 获取前面传下的来的用众筹信息
         * */

        completeScrowView = (ScrollView) findViewById(R.id.complete_scrollView);

        teacherName=(TextView) findViewById(R.id.complete_teacher_name);
        ImageView teacherhead = (ImageView) findViewById(R.id.complete_teacher_head);
        className = (TextView) findViewById(R.id.complete_class_name);
        classTitle = (EditText) findViewById(R.id.complete_class_title);
        classTimeLong =(Button) findViewById(R.id.complete_class_choosetimebtn);
        crowdMoney =(EditText) findViewById(R.id.complete_class_targeymoney);
        crowTime =(EditText) findViewById(R.id.complete_class_crowdingtime);
        crowNumber=(EditText) findViewById(R.id.complete_class_targetNumber);
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.complete_outside_linealayout);


        Intent getIntent = getIntent();

      /**  intent1.putExtra("teacherUrl",temp.getTeacherHeadUrl());
        intent1.putExtra("teacherid",temp.getTeacherId());
        intent1.putExtra("classname",className);
        intent1.putExtra("classid",classId);
        intent1.putExtra("classImageUrl",classImageUrl);//
       */
        String teacherid=getIntent.getStringExtra("teacherid");
        String teacherUrl = getIntent.getStringExtra("teacherUrl");
        String classid= getIntent.getStringExtra("classid");
        String teachername =getIntent.getStringExtra("teachername");
        String classname =getIntent.getStringExtra("classname");
        String classImageUrl = getIntent.getStringExtra("classImageUrl");
        Log.d("TEST",classImageUrl+teacherUrl+"NN");
        className.setText(classname);
        teacherName.setText(teachername);
        Glide
                .with(this)
                .load("http://"+teacherUrl)
                .bitmapTransform(new BlurTransformation(this, 14, 3))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                       linearLayout.setBackground(resource);
                    }
                });
        Glide.with(this).load("http://"+teacherUrl).into(teacherhead);
       Button completeNext = (Button) completeTitleLyaout.findViewById(R.id.my_tool_bar_next);


        completeNext.setText("完成");
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rsb= (RadioButton) findViewById(checkedId);
                RadioButton rsb1= (RadioButton) findViewById(R.id.online);
                RadioButton rsb2= (RadioButton) findViewById(R.id.offline);
                rsb.setTextColor(Color.parseColor("#FF1867FF"));
                if(checkedId ==rsb1.getId()){
                    rsb2.setTextColor(Color.parseColor("#61000000"));
                }
                if(checkedId ==rsb2.getId()){
                    rsb1.setTextColor(Color.parseColor("#61000000"));
                }
            }
        });


        chooseTimeLong =(Button) findViewById(R.id.complete_class_choosetimebtn);
        chooseTimeLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });

        SharedPreferences.Editor editor = getSharedPreferences("iddata",MODE_PRIVATE).edit();
        completeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                SharedPreferences sharedPreferences=getSharedPreferences("iddata",MODE_PRIVATE);
                if(sharedPreferences==null){
                    editor.putInt("id",20);
                    editor.apply();
                    id=sharedPreferences.getInt("id",20);
                } else{
                    id=sharedPreferences.getInt("id",13);
                    int backid=id+1;
                    editor.clear();
                    editor.putInt("id",backid);
                    editor.apply();
                }
                    List<String> stringlist= new ArrayList<>();
                    String classtimeLongtext;
                    classtimeLongtext = chooseTimeLong.getText().toString();
                    String title=classTitle.getText().toString();
                    String mode = "OnLine";
                    String  crowTimetext = crowTime.getText().toString();
                    String   crowdMoneytext = crowdMoney.getText().toString();
                    String crowdumbertext=crowNumber.getText().toString();
                    stringlist.add(classtimeLongtext);
                    stringlist.add(title);
                    stringlist.add(crowdMoneytext);
                    stringlist.add(classtimeLongtext);
                    stringlist.add(crowdumbertext);
                    stringlist.add(crowTimetext);
                    Date date=new Date();
                for(int i=0;i<stringlist.size();i++){
                    if(stringlist.get(i).toString().equals("")){
                        Toast.makeText(Complete.this,"请将信息补充完整",Toast.LENGTH_LONG).show();
                        break;
                    }
                    else if(i==stringlist.size()-1) {
                        SimpleDateFormat format1=new SimpleDateFormat("''yyyy-MM-dd");
                        String date1=format1.format(date).toString();
                        String admin="admin1";
                        int stage=1;
                        String sql="INSERT into raisecourse(id,stage,teacherid,studentid,adminid,price\n" +
                                ",unitprice,createtime,endtime,classtime,title,timeLong) ";              // 生成数据库的语句  把语句传给saveData
                        sql+="VALUE("+"'";
                        sql+="RC"+id+"'"+","+"'";
                        sql+=+stage+"'"+","+"'";
                        sql+=teacherid+"'"+","+"'";
                        sql+="ST6" +"'"+","+"'";
                        sql+=admin+"'"+","+"'";
                        sql+=crowdMoneytext+"'"+","+"'";
                        sql+=""+"'"+",";
                        sql+=date1+"'"+","+"'";
                        sql+=""+"'"+","+"'"+"'";
                        sql+=title+"'"+","+"'";
                        sql+=classTimeLong.getText().toString()+"'"+","+"'";
                        sql+=")";
                        Log.d("SQL",sql);
                        saveData(sql);
                        ActivityCollector.finishAll();
                        Intent intent=new Intent(Complete.this,MainActivity.class);
                        startActivity(intent);
                    }

                    };
            }
        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.set_time_30:
                chooseTimeLong.setText("30");
                mDialog.dismiss();
                break;
            case R.id.set_time_60:
                chooseTimeLong.setText("60");
                mDialog.dismiss();
                break;
            case R.id.set_time_90:
                chooseTimeLong.setText("90");
                mDialog.dismiss();
                break;
            case R.id.set_time:
                break;
            default:
                break;
        }

    }

    public void setDialog(){
         mDialog=new Dialog(this,R.style.choosetimeDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.choose_time_dialog,null);

        mDialog.setContentView(root);
        Window dialogWindow=mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);

        root.findViewById(R.id.set_time_30).setOnClickListener(this);
        root.findViewById(R.id.set_time_60).setOnClickListener(this);
        root.findViewById(R.id.set_time_90).setOnClickListener(this);
        root.findViewById(R.id.set_time).setOnClickListener(this);
        WindowManager.LayoutParams lp=dialogWindow.getAttributes();
        lp.x=0;
        lp.y=0;
        lp.width = (int)getResources().getDisplayMetrics().widthPixels;
        root.measure(0,0);
        lp.height = root.getMeasuredHeight();
      //    lp.alpha=9f;
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
    public void UpData(){

    }
    public  void saveData(String sql){     //在这里做保存数据的工作
        DBconnection dBconnection = new DBconnection();

        new Thread(new Runnable() {
            ResultSet rs=null;
            @Override
            public void run() {

                try{
                    dBconnection.doWriteDB(sql,"quxue");

                }catch (Exception e){

                }
                dBconnection.close();
            }
        }).start();

    }
}
