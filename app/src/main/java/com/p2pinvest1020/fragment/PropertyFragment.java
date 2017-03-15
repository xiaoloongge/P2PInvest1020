package com.p2pinvest1020.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.p2pinvest1020.R;

/**
 * Created by Administrator on 2017/3/10.
 */
public class PropertyFragment extends BaseFragment {


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_property;
    }

    @Override
    public String getChildUrl() {
        return null;
    }
}
