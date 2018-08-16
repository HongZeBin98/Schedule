package com.example.hongzebin.schedule.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hongzebin.schedule.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("cookies", MODE_PRIVATE);
                boolean flag = pref.getString("cookie", "什么都没有").equals("什么都没有");
                Intent intent;
                if (flag) {
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    ScheduleActivity.actionStart(WelcomeActivity.this, null, false);
                }
                WelcomeActivity.this.finish();
            }
        }, 2000);//两秒后跳转到另一个页面
    }
}
