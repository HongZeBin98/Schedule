package com.example.hongzebin.schedule.util;

import android.content.Context;

import com.example.hongzebin.schedule.R;

/**
 * 该类是用来获取颜色id的
 * Created By Mr.Bean
 */
public class ColorUtil {
    /**
     * 通过数字来获取制定的颜色
     * @param context   上下文
     * @param i 需要获取颜色的数字
     * @return 颜色的id
     */
    public static int getColorId(Context context, int i){
        switch (i){
            case 1:
                return context.getResources().getColor(R.color.a1);
            case 2:
                return context.getResources().getColor(R.color.a2);
            case 3:
                return context.getResources().getColor(R.color.a3);
            case 4:
                return context.getResources().getColor(R.color.a4);
            case 5:
                return context.getResources().getColor(R.color.a5);
            case 6:
                return context.getResources().getColor(R.color.a6);
            case 7:
                return context.getResources().getColor(R.color.a7);
            case 8:
                return context.getResources().getColor(R.color.a8);
            case 9:
                return context.getResources().getColor(R.color.a9);
            case 10:
                return context.getResources().getColor(R.color.a10);
            case 11:
                return context.getResources().getColor(R.color.a11);
            case 12:
                return context.getResources().getColor(R.color.a12);
            case 13:
                return context.getResources().getColor(R.color.a13);
            case 14:
                return context.getResources().getColor(R.color.a14);
            case 15:
                return context.getResources().getColor(R.color.a15);
            case 16:
                return context.getResources().getColor(R.color.a16);
            case 17:
                return context.getResources().getColor(R.color.a17);
        }
        return 0;
    }
}
