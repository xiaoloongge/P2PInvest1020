package com.p2pinvest1020.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Administrator on 2017/3/15.
 */

public class DrawableUtils {

    public static Drawable getDrawable(){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.RECTANGLE);//设置成矩形
        drawable.setColor(Color.BLUE); //设置背景色
        drawable.setCornerRadius(UiUtils.dp2px(20)); //设置圆角
        drawable.setStroke(2,Color.RED); //描边 边的颜色
        return drawable;
    }

    public static StateListDrawable getSelector(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片指向状态，未选中图片指向状态
        stateListDrawable.addState(
                new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed},
                pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled},
                normalDrawable);
        //设置默认状态
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }
}
