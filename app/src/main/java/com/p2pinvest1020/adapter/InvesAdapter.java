package com.p2pinvest1020.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.p2pinvest1020.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

/*
* FragmentPagerAdapter
* 会在内存里进行保存 但是不适合fragment较多的情况下
* FragmentStatePagerAdapter
* 在内存里会定期回收掉 所以适合较多的fragment
* */
public class InvesAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments = new ArrayList<>();

    public InvesAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments != null && fragments.size()>0){
            this.fragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
