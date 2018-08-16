package com.example.hongzebin.schedule.util;

import android.content.Context;

import com.example.hongzebin.schedule.R;

public class ColorUtil {
    public static int getColorId(Context context, int i){
        int color;
        switch (i){
            case 1:
                return color = context.getResources().getColor(R.color.a1);
            case 2:
                return color = context.getResources().getColor(R.color.a2);
            case 3:
                return color = context.getResources().getColor(R.color.a3);
            case 4:
                return color = context.getResources().getColor(R.color.a4);
            case 5:
                return color = context.getResources().getColor(R.color.a5);
            case 6:
                return color = context.getResources().getColor(R.color.a6);
            case 7:
                return color = context.getResources().getColor(R.color.a7);
            case 8:
                return color = context.getResources().getColor(R.color.a8);
            case 9:
                return color = context.getResources().getColor(R.color.a9);
            case 10:
                return color = context.getResources().getColor(R.color.a10);
            case 11:
                return color = context.getResources().getColor(R.color.a11);
            case 12:
                return color = context.getResources().getColor(R.color.a12);
            case 13:
                return color = context.getResources().getColor(R.color.a13);
            case 14:
                return color = context.getResources().getColor(R.color.a14);
            case 15:
                return color = context.getResources().getColor(R.color.a15);
            case 16:
                return color = context.getResources().getColor(R.color.a16);
            case 17:
                return color = context.getResources().getColor(R.color.a17);
        }
        return 0;
    }
}
