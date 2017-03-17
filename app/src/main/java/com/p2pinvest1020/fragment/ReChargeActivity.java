package com.p2pinvest1020.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2pinvest1020.R;
import com.p2pinvest1020.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReChargeActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.et_chongzhi)
    EditText etChongzhi;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.yue_tv)
    TextView yueTv;
    @Bind(R.id.btn_chongzhi)
    Button btnChongzhi;

    @Override
    protected void initListener() {

        //
        etChongzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                Log.i("textWatcher", "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("textWatcher", "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = s.toString().trim();
                if (TextUtils.isEmpty(money)){
                    //设置充值不可点击
                    btnChongzhi.setClickable(false);
                    //设置充值的背景颜色
                    btnChongzhi.setBackgroundResource(R.drawable.btn_02);
                }else{
                    btnChongzhi.setClickable(true);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });

        //充值的点击事件
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //调支付宝
                
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_re_charge;
    }

}
