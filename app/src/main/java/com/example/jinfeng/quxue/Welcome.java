package com.example.jinfeng.quxue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jinfeng.quxue.login.Login;
import com.example.jinfeng.quxue.login.LoginSetPassword;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

       /* ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_in));
        viewFlipper.startFlipping();  */  //这里是原来的图片效果

        ImageView temp = (ImageView)findViewById(R.id.back_img);
        temp.setAlpha(0.0f);
        AlphaAnimation alphaAnimationa2 = new AlphaAnimation(0.0f, 0.5f);
        alphaAnimationa2.setDuration(1000);//深浅动画持续时间  
        alphaAnimationa2.setFillAfter(true);
        alphaAnimationa2.setStartOffset(2000);
        temp.setAnimation(alphaAnimationa2);
        temp.setAlpha(0.2f);
        alphaAnimationa2.start();

        final ImageView  logo= (ImageView) findViewById(R.id.logo);
        Animation logoAnim = AnimationUtils.loadAnimation(Welcome.this,R.anim.logo_in);
        logo.setAnimation(logoAnim);
          // 这里应该创建一个新的任务管理区管理 这些活动
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                boolean remenberps=ifRemenber();
                if (remenberps) {
                    Intent intent = new Intent(Welcome.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(Welcome.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);
  // 这里是一个定时任务 定执行任务
    }
    private boolean ifRemenber(){
        SharedPreferences prf=getSharedPreferences("data",MODE_PRIVATE);
        String nick=prf.getString("nickname","");
        if(nick.equals("")){
            return false;
            /**
             *      这里是记住密码的提取过程
             */
        }
        else{
            return true;
        }
    }
}
