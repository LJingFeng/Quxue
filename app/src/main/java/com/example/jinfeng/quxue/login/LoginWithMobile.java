package com.example.jinfeng.quxue.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jinfeng.quxue.R;

public class LoginWithMobile extends AppCompatActivity {

    private EditText telePhoneIn;
    private Button loginWithPassword;
    private Button mobileLoginIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }   //  隐藏ActionBar
        telePhoneIn=(EditText) findViewById(R.id.phone_number_input);

        telePhoneIn.setOnFocusChangeListener(new View.OnFocusChangeListener() {   //这是  EditText的选中时间
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){   // 聚焦之后的动作
                    LinearLayout temp =(LinearLayout) findViewById(R.id.login_with_mobile_layout);
                }
                else{      // 失去焦点之后的动作

                }
            }
        });
        loginWithPassword = (Button) findViewById(R.id.login_with_password);
        loginWithPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginWithMobile.this,LoginWithPassword.class);
                startActivity(intent);
            }
        });
        mobileLoginIn = (Button) findViewById(R.id.mobile_login_in);
        mobileLoginIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginWithMobile.this,LoginSetPassword.class);
                startActivity(intent);
            }
        });
    }
}
