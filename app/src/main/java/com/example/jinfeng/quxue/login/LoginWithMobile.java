package com.example.jinfeng.quxue.login;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.media.Image;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jinfeng.quxue.ActivityCollector;
import com.example.jinfeng.quxue.BaseActivity;
import com.example.jinfeng.quxue.Complete;
import com.example.jinfeng.quxue.MainActivity;
import com.example.jinfeng.quxue.R;
import com.example.jinfeng.quxue.message.SMS;
import cn.smssdk.SMSSDK;
public class LoginWithMobile extends BaseActivity {
    private EditText telePhoneIn;
    private ImageView setal;
    private EditText SMScode;//zhl 加的textview
    private Button loginWithPassword;
    private Button mobileLoginIn;
    private Button loginWithQQ;
    private Button regetCode;
    private Button getSMScode;//zhl加的button
    private LinearLayout phoneNumberLayout;
    private LinearLayout codeReceiveLayout;
    private  String TELE_NUMBER;
    private String  telenumber;
    private SMS sms=new SMS();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        loginWithPassword = (Button) findViewById(R.id.telephone_login_with_password);
        loginWithQQ = (Button) findViewById(R.id.login_with_qq);
        regetCode = (Button)findViewById(R.id.reget_code);
        getSMScode=(Button)findViewById(R.id.message_send);//zhl add
        SMScode=(EditText)findViewById(R.id.zhl_smscode_input) ;
        telePhoneIn=(EditText) findViewById(R.id.telephone_number_input);
        phoneNumberLayout =(LinearLayout) findViewById(R.id.telenumber_layout);
        codeReceiveLayout =(LinearLayout)findViewById(R.id.code_layout);
        mobileLoginIn = (Button) findViewById(R.id.mobile_login_in);
        View.OnClickListener myOnClickListener=new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.telephone_login_with_password:
                        Intent intent=new Intent(LoginWithMobile.this,LoginWithPassword.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };
        loginWithPassword.setOnClickListener(myOnClickListener);
        setal=(ImageView)findViewById(R.id.alpha_set);
        setal.setAlpha(0.7f);
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }   //  隐藏ActionBar
        /**telePhoneIn.setOnFocusChangeListener(new View.OnFocusChangeListener() {   //这是  EditText的选中时间
         @Override
         public void onFocusChange(View v, boolean hasFocus) {
         if(hasFocus){   // 聚焦之后的动作
         LinearLayout temp =(LinearLayout) findViewById(R.id.login_with_mobile_layout);
         }
         else{      // 失去焦点之后的动作
         }
         }
         });
         */
        loginWithPassword.setAlpha(0.5f);
        loginWithPassword.setEnabled(false);
        getSMScode.setEnabled(false);
        getSMScode.setAlpha(0.5f);
        telePhoneIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>10){
                    loginWithPassword.setAlpha(1.0f);
                    loginWithPassword.setEnabled(true);
                    getSMScode.setEnabled(true);
                    getSMScode.setAlpha(1.0f);
                }
                else{
                    loginWithPassword.setAlpha(0.5f);
                    loginWithPassword.setEnabled(false);
                    getSMScode.setEnabled(false);
                    getSMScode.setAlpha(0.5f);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mobileLoginIn.setAlpha(0.5f);
        mobileLoginIn.setEnabled(false);
        mobileLoginIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                if(sharedPreferences!=null){
                    SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.clear();
                }
                String smscode=SMScode.getText().toString();
                sms.compareSMScode(telenumber,smscode);
                if(sms.ismatched==true){
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(LoginWithMobile.this,MainActivity.class);
                    intent.putExtra("telephone",telenumber);
                    startActivity(intent);
                    Toast.makeText(LoginWithMobile.this,"验证成功",Toast.LENGTH_LONG).show();
                    boolean ifexist=false;    // 判断数据是否已经存在数据库
                    boolean ifFirstLogin =false;   // 判断是否是第一次登录
                    if(ifexist){
                        if(ifFirstLogin){
                            // 若为第一次登录则进入设置密码的界面
                        }else{
                            // 不是第一次登录直接登录系统
                        }
                    }else{
                        // 存入数据库
                    }
                }
                else{
                    Toast.makeText(LoginWithMobile.this,"验证失败"+sms.consolecompareSMScode,Toast.LENGTH_LONG).show();
                }
                sms.consolecompareSMScode="";
                sms.ismatched=false;//由于至此sms对象已使用过一次并且未被释放，所以，这些成员变量需要手动“归零”
                sms.compareSMScodeEventHandler=null;
            }
        });
        getSMScode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                telenumber=telePhoneIn.getText().toString();
                sms.GetMessage(telenumber);
                if(sms.issuccess==true){

                    Toast.makeText(LoginWithMobile.this,"短信已发送",Toast.LENGTH_LONG).show();
                    mobileLoginIn.setEnabled(true);
                    phoneNumberLayout.setVisibility(View.GONE);
                    codeReceiveLayout.setVisibility(View.VISIBLE);
                    mobileLoginIn.setAlpha(1.0f);
                    regetCode.setAlpha(0.5f);
                    CountDownTimer timer = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            regetCode.setText((millisUntilFinished / 1000) + "秒后可重发");
                        }
                        @Override
                        public void onFinish() {
                            regetCode.setEnabled(true);
                            regetCode.setText("获取验证码");regetCode.setAlpha(1.0f);
                        }
                    }.start();
                }
                else{
                    Toast.makeText(LoginWithMobile.this,"未能发送短信"+sms.consoleGetMessage,Toast.LENGTH_LONG).show();
                }
                sms.issuccess=true;
                sms.consoleGetMessage="";//“归零”这里包括前面的值都是初值
                sms.GetMessageEventHandler=null;
            }
        });
        loginWithQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                Intent intent=new Intent(LoginWithMobile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showButtonEnable(Button btn){
        AlphaAnimation anim=new AlphaAnimation(0.5f,1.0f);
        anim.setDuration(500);
        btn.setAnimation(anim);
        anim.start();
    }
}