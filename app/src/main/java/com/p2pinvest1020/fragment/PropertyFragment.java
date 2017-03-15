package com.p2pinvest1020.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p2pinvest1020.R;
import com.p2pinvest1020.activity.BaseActivity;
import com.p2pinvest1020.activity.MainActivity;
import com.p2pinvest1020.bean.UserInfo;
import com.p2pinvest1020.command.AppNetConfig;
import com.p2pinvest1020.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/3/10.
 */
public class PropertyFragment extends BaseFragment {


    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        MainActivity activity = (MainActivity) getActivity();
        UserInfo user = activity.getUser();
        //设置用户名
        tvMeName.setText(user.getData().getName());

        //设置头像
       /* Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {

                        Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);

                        bitmap.recycle(); //必须把原来的释放掉
                        return circleBitmap;
                    }

                    @Override
                    public String key() {
                        return ""; //不能为空否则会报错
                    }
                })
                .into(ivMeIcon);*/
        Picasso.with(getActivity()).load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new CropCircleTransformation())
                .transform(
                        new ColorFilterTransformation(Color
                                .parseColor("#66FFccff")))
                //第二个参数值越大越模糊
                .transform(new BlurTransformation(getActivity(),80))
                .into(ivMeIcon);
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
