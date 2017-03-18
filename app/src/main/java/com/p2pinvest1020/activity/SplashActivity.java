package com.p2pinvest1020.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.p2pinvest1020.R;
import com.p2pinvest1020.bean.UpdateBean;
import com.p2pinvest1020.command.AppNetConfig;
import com.p2pinvest1020.utils.AppManager;
import com.p2pinvest1020.utils.LoadNet;
import com.p2pinvest1020.utils.LoadNetHttp;
import com.p2pinvest1020.utils.ThreadPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

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
        checkUpdate();
    }

    private void checkUpdate() {

        if (isOnLine()) {
            //连网
            LoadNet.getDataPost(AppNetConfig.UPDATE, new LoadNetHttp() {
                @Override
                public void success(String context) {
                    Log.i("update", "success: "+context);
                    final UpdateBean updateBean = JSON.parseObject(context, UpdateBean.class);
                    //判断当前的版本号
                    if (!getVersion().equals(updateBean.getVersion())){
                        //提示有新版本
                        new AlertDialog.Builder(SplashActivity.this)
                                .setTitle("有新版本你怎么看？")
                                .setMessage(updateBean.getDesc())
                                .setPositiveButton("不差钱", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        updateVerison(updateBean.getApkUrl());
                                    }
                                })
                                .setNegativeButton("哥很穷", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        toMain();
                                    }
                                })
                                .show();
                    }else{
                        //直接进入主界面
                        toMain();
                    }

                }

                @Override
                public void failure(String error) {
                    Log.i("update", "error: "+error);
                }
            });
        }else{
            //不要在动画没有执行完之前做进入主界面的动作
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    showToast("当前没有网络");
                    toMain();
                }
            },2000);
        }
    }

    private void updateVerison(final String apkUrl) {
        //展示进度条
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        //下载
        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {

            private FileOutputStream os;
            private InputStream inputStream;

            @Override
            public void run() {
                try {
                    //获取url地址
                    URL url = new URL(AppNetConfig.BASE_URL+"app_new.apk");
                    //打开连接
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(5000);//读取超时
                    conn.setConnectTimeout(5000);//连接超时
                    conn.setRequestMethod("GET");//请求方式
                    conn.connect();//连接网络

                    if (conn.getResponseCode() == 200){//连网成功
                        //进度条的总长度
                        progressDialog.setMax(conn.getContentLength());

                        inputStream = conn.getInputStream();

                        File path;
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            path = getExternalFilesDir("");
                        }else{
                            path = getFilesDir();
                        }
                        File file = new File(path, "update.apk");
                        os = new FileOutputStream(file);

                        byte[] bytes = new byte[1024];
                        int len;
                        /*
                        * inputStream.read(bytes) 将数据装到bytes数组里
                        * */
                        while ((len = inputStream.read(bytes))!=-1){
                            progressDialog.incrementProgressBy(len);
                            os.write(bytes,0,len);
                        }

                        //下载成功了
                        progressDialog.dismiss();
                        //安装
                        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                        intent.setData(Uri.parse("file:" + file.getAbsolutePath()));
                        startActivity(intent);
                    }else{//连网失败
                        showToast("连网失败你丫看着办");
                        toMain();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (os != null){
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

    }

    public void toMain(){
        if (isLogin()) {
            //登录过进入主界面
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            //没有登录过进入登录界面
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    //判断是否有网络
    private boolean isOnLine() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
       // return connected;
        return true;
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

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplash.startAnimation(animation);
    }

    private boolean isLogin() {
        String name = getUser().getData().getName();
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        return true;
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
