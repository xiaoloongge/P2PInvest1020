package com.p2pinvest1020.utils;

import android.util.Log;

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
    }
}
