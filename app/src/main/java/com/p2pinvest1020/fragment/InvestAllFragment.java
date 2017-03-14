package com.p2pinvest1020.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.p2pinvest1020.R;
import com.p2pinvest1020.adapter.InvestAllAdapter;
import com.p2pinvest1020.adapter.InvestAllAdapter1;
import com.p2pinvest1020.adapter.InvestAllAdapter2;
import com.p2pinvest1020.adapter.InvestAllAdapter3;
import com.p2pinvest1020.bean.InvestAllBean;
import com.p2pinvest1020.command.AppNetConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        //Log.i("aaa", "initData: "+json);
        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);

        /*InvestAllAdapter adapter =
                new InvestAllAdapter(investAllBean.getData());*/
        /*InvestAllAdapter1 adapter =
                new InvestAllAdapter1(investAllBean.getData());*/
        /*InvestAllAdapter2 adapter =
                new InvestAllAdapter2(investAllBean.getData());*/
        InvestAllAdapter3 adapter =
                new InvestAllAdapter3(investAllBean.getData());

        investAllLv.setAdapter(adapter);
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

}
