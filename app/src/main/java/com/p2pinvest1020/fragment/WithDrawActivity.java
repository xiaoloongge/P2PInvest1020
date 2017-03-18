package com.p2pinvest1020.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.p2pinvest1020.R;
import com.p2pinvest1020.activity.BaseActivity;
import com.p2pinvest1020.activity.GestureEditActivity;
import com.p2pinvest1020.activity.GestureVerifyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WithDrawActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.et_input_money)
    EditText etInputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = s.toString().trim();
                if (TextUtils.isEmpty(money)){
                    btnTixian.setClickable(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                }else{
                    btnTixian.setClickable(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });

        btnTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("tog_state", Context.MODE_PRIVATE);
                boolean isOpen = sp.getBoolean("isOpen", false);
                //验证手势密码
                if (isOpen){
                    startActivity(new Intent(WithDrawActivity.this,
                            GestureVerifyActivity.class));
                }else{
                    Toast.makeText(WithDrawActivity.this, "提现成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void initTitle() {

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        baseTitle.setText("提现");

        baseSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_with_draw;
    }

}
