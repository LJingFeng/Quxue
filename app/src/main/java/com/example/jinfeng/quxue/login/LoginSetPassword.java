package com.example.jinfeng.quxue.login;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.jinfeng.quxue.R;

public class LoginSetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_set_password);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }   //  隐藏ActionBar
    }
}
