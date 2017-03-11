package com.p2pinvest1020.utils;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/11.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler(){};

    private static  CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance(){
        return crashHandler;
    }

    public void init(){
        //把当前的类设置成默认的处理未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.i("uncaughtException", "uncaughtException: ");

        new Thread(){
            @Override
            public void run() {
                super.run();
                /*
                * Looper.prepare() Looper.loop()  中间是在主线程中运行
                *
                * 一个线程只有一个looper
                * */
                Looper.prepare();
                Toast.makeText(UiUtils.getContext(), "aaa", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collection(e);
        //销毁所有的activity
        AppManager.getInstance().removeAll();
        //结束当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //关闭虚拟机 0正常退出 其它都是非正常退出
        System.exit(0);
    }

    private void collection(Throwable e) {

        //把崩溃信息返回给服务器
        /*
        *
        * android.os.Build
从系统属性中提取设备硬件和版本信息。

常用属性如下：
Build.BOARD // 主板
Build.BRAND // android系统定制商
Build.CPU_ABI // cpu指令集
Build.DEVICE // 设备参数
Build.DISPLAY // 显示屏参数
Build.FINGERPRINT // 硬件名称
Build.HOST
Build.ID // 修订版本列表
Build.MANUFACTURER // 硬件制造商
Build.MODEL // 版本
Build.PRODUCT // 手机制造商
Build.TAGS // 描述build的标签
Build.TIME
// 当前开发代号
Build.VERSION.CODENAME
// 源码控制版本号
Build.VERSION.INCREMENTAL
// 版本字符串
Build.VERSION.RELEASE
// 版本号
Build.VERSION.SDK
// 版本号
Build.VERSION.SDK_INT
Build.TYPE // builder类型
Build.USER

        *
        * */
    }
}
