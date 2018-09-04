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
import com.example.hongzebin.schedule.presenter.LoginPresenter;
import com.example.hongzebin.schedule.util.JsoupUtil;

/**
 * 登录Activity
 * Created By Mr.Bean
 */
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

    /**
     * 初始化视图
     */
    private void initView() {
        mBtLogin = findViewById(R.id.id_login_enter);
        mEtPassword = findViewById(R.id.id_login_password);
        mEtUserName = findViewById(R.id.id_login_username);
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化事件处理
     */
    private void initEvent() {
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPresenter loginPresenter = new LoginPresenter();
                loginPresenter.ifSimulateLogin(LoginActivity.this, mEtUserName, mEtPassword, new LoginPresenter.LoginCallBack() {
                    @Override
                    public void onException() {
                        Toast.makeText(LoginActivity.this, "服务器繁忙，请稍后重试。", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
