package com.example.jinfeng.quxue.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by JinFeng on 2017/9/23.
 */

public class MyFrameLayoutAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentPagerAdapter;
    private List<Fragment> listfragment;
    public MyFrameLayoutAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.fragmentPagerAdapter = fm;
        this.listfragment = list;
    }

    @Override
    public Fragment getItem(int arg0){
        return  listfragment.get(arg0);
    }
    @Override
    public int getCount(){
        return listfragment.size();
    }
}
