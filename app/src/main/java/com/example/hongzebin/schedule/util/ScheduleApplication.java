package com.example.hongzebin.schedule.util;

import android.content.Context;
import android.util.Log;

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
