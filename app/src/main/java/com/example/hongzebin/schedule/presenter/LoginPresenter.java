package com.example.hongzebin.schedule.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;

import com.example.hongzebin.schedule.util.JsoupUtil;

/**
 * 对LoginActivity的数据进行处理
 * Created By Mr.Bean
 */
public class LoginPresenter {

    public interface LoginCallBack{
        void onException();
    }

    /**
     * 判断模拟登录是否成功
     * @param context 上下文
     * @param etUserName 输入的用户名
     * @param etPassword 输入的密码
     * @param callBack  接口
     */
    public void ifSimulateLogin(final Context context, EditText etUserName, EditText etPassword, final LoginCallBack callBack){
        JsoupUtil jsoupUtil = new JsoupUtil();
        jsoupUtil.simulateLogin(context, etUserName.getText().toString(), etPassword.getText().toString(), new JsoupUtil.HttpCallBackListener() {
            @Override
            public void onException() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onException();
                    }
                });
            }
        });
    }
}
