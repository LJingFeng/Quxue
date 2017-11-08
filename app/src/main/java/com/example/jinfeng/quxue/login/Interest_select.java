package com.example.jinfeng.quxue.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.example.jinfeng.quxue.Adapter.InterestSelectAdapter;
import com.example.jinfeng.quxue.BaseActivity;
import com.example.jinfeng.quxue.MainActivity;
import com.example.jinfeng.quxue.MyType.MyInterest;
import com.example.jinfeng.quxue.R;

import java.util.ArrayList;
import java.util.List;

public class Interest_select extends BaseActivity {
    private List<MyInterest> mList = new ArrayList<>();
    private Button okbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_select);
        ActionBar actonbar = getSupportActionBar();
        if(actonbar!=null){
            actonbar.hide();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initCheckBox();
        InterestSelectAdapter interestSelectAdapter=new InterestSelectAdapter(mList);
        RecyclerView  myrecycler = (RecyclerView) findViewById(R.id.recycler1);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        myrecycler.setLayoutManager(staggeredGridLayoutManager);
        myrecycler.setAdapter(interestSelectAdapter);

        okbtn = (Button) findViewById(R.id.ok);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainActivity=new Intent(Interest_select.this, MainActivity.class);
                startActivity(toMainActivity);
                finish();
            }
        });
    }

    public void initCheckBox(){
        for(int i=1;i<10;i++){
            int n=1;
            String name="interest"+n;
            MyInterest interest1=new MyInterest(name,"A",n);
            mList.add(interest1);
            n++;
        }
    }
}
