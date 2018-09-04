package com.example.hongzebin.schedule.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hongzebin.schedule.R;

/**
 * 一打开APP的跳转预览图，会进行判断是否需要登录
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //通过延时发送使画面有持续时间
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否有本地cookies
                SharedPreferences pref = getSharedPreferences("cookies", MODE_PRIVATE);
                boolean flag = pref.getString("cookie", "什么都没有").equals("什么都没有");
                Intent intent;
                if (flag) {
                    //如果没有本地cookies就证明数据库中没有相关的课表信息，则跳转到登录activity进行登录
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //如果有本地cookies就证明数据库中有课表信息，则直接跳转到课表的显示activity
                    ScheduleActivity.actionStart(WelcomeActivity.this, null, false);
                }
                //结束该活动
                WelcomeActivity.this.finish();
            }
        }, 2000);//两秒后跳转到另一个页面
    }
}
