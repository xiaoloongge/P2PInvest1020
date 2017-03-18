package com.p2pinvest1020.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.p2pinvest1020.R;
import com.p2pinvest1020.activity.GestureEditActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/10.
 */
public class MoreFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @Bind(R.id.toggle_more)
    ToggleButton toggleMore;
    @Bind(R.id.tv_more_reset)
    TextView tvMoreReset;
    @Bind(R.id.tv_more_phone)
    TextView tvMorePhone;
    @Bind(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @Bind(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_about)
    TextView tvMoreAbout;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        initTitle();

        //设置手势密码
        startActivity(new Intent(getActivity(), GestureEditActivity.class));
    }

    private void initTitle() {

       baseBack.setVisibility(View.GONE);
        baseTitle.setText("设置更多");
        baseSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_more;
    }

    @Override
    public String getChildUrl() {
        return null;
    }

}
