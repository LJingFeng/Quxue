package com.example.jinfeng.quxue.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinfeng.quxue.BaseActivity;
import com.example.jinfeng.quxue.R;
import com.example.jinfeng.quxue.message.SMS;
public class InforInput extends BaseActivity {
    private Button Loginbtn;
    private ImageView imageView;
    private TextView showWrong;
    private TextView CodeReceive;
    private Button reSendMsg;
    private SMS sms=new SMS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_input);
        Loginbtn=(Button) findViewById(R.id.mobile_login_in);
        showWrong =(TextView) findViewById(R.id.show_wrong);
        CodeReceive =(TextView) findViewById(R.id.Code_input);
        imageView = (ImageView) findViewById(R.id.input_back);
        reSendMsg =(Button) findViewById(R.id.resend_msg);
        imageView.setAlpha(0.7f);
        Intent receiveIntent = getIntent();
        String telePhoneNumber=receiveIntent.getStringExtra("telephone");  // 接受上一活动传递的电话号码
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  String code=CodeReceive.toString();
                 boolean ifCorrect=false;
                ifCorrect=sms.compareSMScode(telePhoneNumber,code);
                 if(ifCorrect){
                    Toast.makeText(InforInput.this, "hao", Toast.LENGTH_SHORT).show();
                }
                // 在这里比较验证码 判断true or、 flase;
             /*   if(true){
                                                         //在这里比较数据库是否是第一次登录
                    if(true){
                        Intent intentToSetPs=new Intent(InforInput.this,LoginSetPassword.class);
                        startActivity(intentToSetPs);//  第一次的话就跳转到设置密码界面
                    }else{
                        Intent intentToMain=new Intent(InforInput.this, MainActivity.class);
                        startActivity(intentToMain);
                    }
                                                        //这里比较验证码
                }else{
                        showWrong.setText("验证码输入错误");
                }*/
            }
        });
        reSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTimeDownCount myTimeDownCount=new MyTimeDownCount();
                myTimeDownCount.start();
            }
        });
    }
    class MyTimeDownCount extends CountDownTimer{
    public MyTimeDownCount() {
        super(10000, 1000);
    }
    @Override
    public void onTick(long millisUntilFinished) {
        reSendMsg.setEnabled(false);
        // 每countDownInterval触发一次onTick事件
        reSendMsg.setText(millisUntilFinished / 1000 + "再次发送");
    }
    @Override
    public void onFinish() {
        reSendMsg.setEnabled(true);
        reSendMsg.setText("发送");
    }

}


}
