package com.example.hongzebin.schedule.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.util.Log;

import com.example.hongzebin.schedule.activity.ScheduleActivity;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class JsoupUtil {
    private static String LOGIN_URL = "http://authserver.gdut.edu.cn/authserver/login?service=http%3A%2F%2Fjxfw.gdut.edu.cn%2Fnew%2FssoLogin";
    private static String USER_AGENT = "User-Agent";
    private static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";


    public interface HttpCallBackListener {
        void onException();
    }

    public void simulateLogin(final Context context, final String userName, final String password,  final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //第一次请求
                Connection con = Jsoup.connect(LOGIN_URL);  //获取connection
                con.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
                Response rs = null;                // 获取响应
                try {
                    rs = con.execute();
                } catch (IOException e) {
                    Log.e("JsoupUtil", Log.getStackTraceString(e) );
                    listener.onException();
                }
                Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
                List<Element> eleList = d1.select("#casLoginForm");  // 获取提交form表单
                // 获取cooking和表单属性
                Map<String, String> data = new HashMap<>();
                for (Element e : eleList.get(0).getAllElements()) {
                    // 设置用户名
                    if (e.attr("name").equals("username")) {
                        e.attr("value", userName);
                    }
                    // 设置用户密码
                    if (e.attr("name").equals("password")) {
                        e.attr("value", password);
                    }
                    // 排除空值表单属性
                    if (e.attr("name").length() > 0) {
                        data.put(e.attr("name"), e.attr("value"));
                    }
                }
                //第二次请求，以post方式提交表单数据以及cookie信息
                Connection con2 = Jsoup.connect(LOGIN_URL);  //获取connection
                con2.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
                Response response = null;
                try {
                    // 设置cookie和post上面的map数据
                    Response login = con2.method(Connection.Method.POST)
                            .data(data)
                            .cookies(rs.cookies())
                            .execute();
                    //获取课程表信息的html信息
                    Connection con3 = Jsoup.connect("http://jxfw.gdut.edu.cn/xsgrkbcx!xsAllKbList.action?xnxqdm=201801");
                    con3.header(USER_AGENT, USER_AGENT_VALUE);
                    response = con3.method(Connection.Method.GET)
                            .ignoreContentType(true)
                            .followRedirects(true)
                            .cookies(login.cookies())
                            .execute();
                    //保存cookies
                    saveCookies(context, login.cookies());
                    //从html信息中截取到课程表的json信息
                    Document document = Jsoup.parse(response.body());
                    String script = document.select("script").last().toString();
                    String[] str = script.split(";");
                    String json = str[1].substring(14);
                    //把解析到的数据传到课程表activity
                    ScheduleActivity.actionStart(context, json, true);
                } catch (IOException e) {
                    Log.e("JsoupUtil", Log.getStackTraceString(e) );
                    listener.onException();
                }
            }
        }).start();
    }

    /**
     * 保存cookies
     * @param context   上下文
     * @param cookies   需要保存的cookies
     */
    private void saveCookies(Context context, Map<String, String> cookies){
        SharedPreferences.Editor editor = context.getSharedPreferences("cookies"
                ,  MODE_PRIVATE).edit();
        editor.putString("cookie", cookies.toString());
        editor.apply();
    }

//    public void getScheduleJson(final String url, final HttpCallBackListener listener) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Connection con = Jsoup.connect(url);
//                con.header(USER_AGENT, USER_AGENT_VALUE);
//                try {
//                    Response response = con.method(Connection.Method.GET)
//                            .cookies(mCookies)
//                            .execute();
//                    listener.onFinish(response.body());
//                } catch (IOException e) {
//                    Log.e("JsoupUtil", Log.getStackTraceString(e));
//                }
//            }
//        }).start();
//    }
}
