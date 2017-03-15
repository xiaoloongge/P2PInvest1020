package com.p2pinvest1020.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BitmapUtils {

   /*
   ALPHA_8 代表8位Alpha位图
   ARGB_4444 代表16位ARGB位图
   ARGB_8888 代表32位ARGB位图
   RGB_565 代表8位RGB位图

    一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，
    8位=1字节，所以一个像素占4字节的内存。假设有一张480x800的图片，
    如果格式为ARGB_8888，那么将会占用1500KB的内存。*/


    public static Bitmap circleBitmap(Bitmap source) {
        //获取Bitmap的宽度
        int width = source.getWidth();
        //以Bitmap的宽度值作为新的bitmap的宽高值。
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        //以此bitmap为基准，创建一个画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //在画布上画一个圆
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有：
        //PorterDuff.Mode.SRC_IN 取两层图像交集部分,只显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部分,只显示下层图像
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //在画布上绘制bitmap
        canvas.drawBitmap(source, 0, 0, paint);
        return bitmap;

    }

    //实现图片的压缩处理
    //设置宽高必须使用浮点型，否则导致压缩的比例：0
    public static Bitmap zoom(Bitmap source,float width ,float height){
        Matrix matrix = new Matrix();
        //图片的压缩处理
        matrix.postScale(width / source.getWidth(),height / source.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(), source.getHeight(), matrix, false);
        return bitmap;
    }
}
