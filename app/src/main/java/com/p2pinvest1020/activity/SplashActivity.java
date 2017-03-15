package com.p2pinvest1020.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.p2pinvest1020.R;
import com.p2pinvest1020.utils.AppManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;


    @Override
    protected void initListener() {

    }

    public void initData() {
        AppManager.getInstance().addActivity(this);
        //设置版本号
        setVersion();
        //设置动画
        setAnimation();
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_splash2;
    }

    private void setAnimation() {

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        //动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画执行完
            @Override
            public void onAnimationEnd(Animation animation) {

                if (isLogin()){
                   //登录过进入主界面
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    //没有登录过进入登录界面
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplash.startAnimation(animation);
    }

    private boolean isLogin() {
        return false;
    }

    private void setVersion() {
        splashTvVersion.setText(getVersion());
    }

    private String getVersion() {

        try {
            //拿到包的管理器
            PackageManager packageManager = getPackageManager();
            //拿到包的信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //versionCode每次发布新版本一定要加1
            int versionCode = packageInfo.versionCode;
            //当前的版本号
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //AppManager.getInstance().removeActivity(this);
        //AppManager.getInstance().removeCurrentActivity();
        AppManager.getInstance().remove(this);
    }
}
