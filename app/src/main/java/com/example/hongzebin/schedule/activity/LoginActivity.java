package com.example.hongzebin.schedule.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongzebin.schedule.R;
import com.example.hongzebin.schedule.util.JsoupUtil;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mBtLogin = findViewById(R.id.id_login_enter);
        mEtPassword = findViewById(R.id.id_login_password);
        mEtUserName = findViewById(R.id.id_login_username);
    }

    private void initData() {

    }

    private void initEvent() {
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsoupUtil jsoupUtil = new JsoupUtil();
                jsoupUtil.simulateLogin(LoginActivity.this, mEtUserName.getText().toString(), mEtPassword.getText().toString(), new JsoupUtil.HttpCallBackListener() {
                    @Override
                    public void onException() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "服务器繁忙，请稍后重试。", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
