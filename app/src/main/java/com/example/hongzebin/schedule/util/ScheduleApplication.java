package com.example.hongzebin.schedule.util;

import android.content.Context;
import android.util.Log;

/**
 * 该类是全局获取Context
 * Created By Mr.Bean
 */
public class ScheduleApplication extends android.app.Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        mContext =getApplicationContext();
        super.onCreate();
    }

    //获取context
    public static Context getmContext() {
        return mContext;
    }
}
