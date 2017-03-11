package com.p2pinvest1020.utils;

import android.content.Context;
import android.view.View;

import com.p2pinvest1020.command.MyApplication;

/**
 * Created by Administrator on 2017/3/11.
 *
 *
 * 和UI相关的一些操作类
 */

public class UiUtils {


    public static Context getContext(){
        return MyApplication.getContext();
    }

    public static View getView(int layoutid){
        return View.inflate(getContext(),layoutid,null);
    }

    public static int getColor(int color){
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArrary(int stringid){
        return getContext().getResources().getStringArray(stringid);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }


}
