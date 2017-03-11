package com.p2pinvest1020.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.p2pinvest1020.R;
import com.p2pinvest1020.command.AppNetConfig;
import com.p2pinvest1020.utils.LoadNet;
import com.p2pinvest1020.utils.LoadNetHttp;
import com.p2pinvest1020.utils.UiUtils;

/**
 * Created by Administrator on 2017/3/10.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = UiUtils.getView(R.layout.fragment_home);
        View view = View.inflate(getActivity(),R.layout.fragment_home,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {

        /*
        * 二次封装
        * 为什么要二次封装
        *
        * 第一  调用的方便
        * 第二  修改和维护方便
        * */
        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                Log.i("http", "success: "+context);
            }

            @Override
            public void failure(String error) {
                Log.i("http", "failure: "+error);
            }
        });
    }
}
