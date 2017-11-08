package com.example.jinfeng.quxue.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.jinfeng.quxue.BaseActivity;
import com.example.jinfeng.quxue.MainActivity;
import com.example.jinfeng.quxue.R;


public class LoginSetPassword extends BaseActivity {
    private Button resetPsLater;

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
        }
        resetPsLater=(Button) findViewById(R.id.set_later);
        resetPsLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginSetPassword.this, Interest_select.class);
                startActivity(intent);
            }
        });//  隐藏ActionBar

    }
}
