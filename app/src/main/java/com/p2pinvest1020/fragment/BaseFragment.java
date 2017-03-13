package com.p2pinvest1020.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.p2pinvest1020.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/13.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = View.inflate(getActivity(),getLayoutid(),null);
        loadingPager = new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {
                ButterKnife.bind(BaseFragment.this,sucessView);
                initData(resultState.getJson());
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutid();
            }
        };

        return loadingPager;
    }

  

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        //initData();
        //初始化监听
        //initListener();
        loadingPager.loadData();
    }

    protected abstract void initListener();

    protected abstract void initData(String json);


    public abstract int getLayoutid();

    //每一个fragment返回的地址
    public abstract String getChildUrl();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
