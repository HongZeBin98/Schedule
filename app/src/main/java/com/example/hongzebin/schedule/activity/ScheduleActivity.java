package com.example.hongzebin.schedule.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongzebin.schedule.R;
import com.example.hongzebin.schedule.bean.Course;
import com.example.hongzebin.schedule.bean.TableModel;
import com.example.hongzebin.schedule.greendao.GreenDaoManager;
import com.example.hongzebin.schedule.model.ScheduleModel;
import com.example.hongzebin.schedule.presenter.SchedulePresenter;
import com.example.hongzebin.schedule.util.ColorUtil;
import com.example.hongzebin.schedule.util.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 课程表显示Activity
 * Created By Mr.Bean
 */
public class ScheduleActivity extends AppCompatActivity {

    private Button mBtWeeksChoice;
    private Button mBtLogOff;
    private List<TableModel> mTableModelList;
    private List<TableModel> mAllTableModelList;
    private GreenDaoManager mGreenDaoManager;
    private RelativeLayout mRlWeekDay;
    private int mColor;
    private String mJson;
    private boolean mFlag;
    private SchedulePresenter mSchedulePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initView();
        initData();
        initEvent();
    }

    /**
     * 动态添加左侧课程节次
     */
    @SuppressLint("SetTextI18n")
    private void createCourseCount() {
        LinearLayout rootLayout = findViewById(R.id.left_view_layout);
        for (int i = 1; i <= 12; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 250);
            //设置显示节次信息
            TextView textView = new TextView(this);
            textView.setText("" + i);
            int color = getResources().getColor(R.color.black);
            textView.setTextColor(color);
            textView.setGravity(Gravity.CENTER);
            rootLayout.addView(textView, params);
        }
    }

    /**
     * 把某一门课程加入课程表
     *
     * @param tableModel 某一门课程
     */
    @SuppressLint("SetTextI18n")
    private void createCourseView(TableModel tableModel) {
        int height = 250;
        List<String> courseTime = tableModel.getTime();
        switch (tableModel.getWeekday()) {
            case "1":
                mRlWeekDay = findViewById(R.id.monday);
                break;
            case "2":
                mRlWeekDay = findViewById(R.id.tuesday);
                break;
            case "3":
                mRlWeekDay = findViewById(R.id.wednesday);
                break;
            case "4":
                mRlWeekDay = findViewById(R.id.thursday);
                break;
            case "5":
                mRlWeekDay = findViewById(R.id.friday);
                break;
            case "6":
                mRlWeekDay = findViewById(R.id.saturday);
                break;
            case "7":
                mRlWeekDay = findViewById(R.id.sunday);
                break;
        }
        //因为只有17个颜色可以选择，如果大于17将没有相应的颜色进行匹配
        if (mColor == 17) {
            mColor -= 16;
        } else {
            mColor++;
        }
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(this).inflate(R.layout.course_card, null); //加载单个课程布局
        //设置开始高度，即该课由第几节开始
        int startCourseTime = Integer.parseInt(courseTime.get(0));
        v.setY(height * (startCourseTime - 1));
        //设置颜色
        v.setBackgroundColor(ColorUtil.getColorId(ScheduleActivity.this, mColor));
        //设置布局高度，即该课连续上几节
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , courseTime.size() * height);
        v.setLayoutParams(params);
        TextView textView = v.findViewById(R.id.course_text);
        //显示课程名和地址
        textView.setText(tableModel.getName() + "@" + tableModel.getPlace());
        textView.setGravity(Gravity.CENTER);
        mRlWeekDay.addView(v);

    }

    /**
     * 初始化界面
     */
    private void initView() {
        mSchedulePresenter = new SchedulePresenter();
        mTableModelList = new ArrayList<>();
        mAllTableModelList = new ArrayList<>();
        mBtLogOff = findViewById(R.id.id_log_off);
        mBtWeeksChoice = findViewById(R.id.id_weeks);
        Toolbar toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mGreenDaoManager = GreenDaoManager.Holder.getInstance();
        Intent intent = getIntent();
        mJson = intent.getStringExtra("Json");
        mFlag = intent.getBooleanExtra("flag", true);
        mColor = 0;
    }

    /**
     * 初始化事件处理
     */
    private void initEvent() {
        //注销
        mBtLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOff();
            }
        });
        //星期选择
        mBtWeeksChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWeeks();
            }
        });
        createCourseCount();
        mSchedulePresenter.loadData(mFlag, mJson, new SchedulePresenter.schedulePresenterCallBack() {
            @Override
            public void onLoading(TableModel tableModel) {
                createCourseView(tableModel);
            }

            @Override
            public void onFinish(List<TableModel> list) {
                mAllTableModelList = list;
            }
        });
    }

    /**
     * 设置提示对话框，是否确定注销账户
     */
    private void logOff() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ScheduleActivity.this);
        dialog.setTitle("你确认注销该账户吗？");
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除数据库
                mGreenDaoManager.deleteAll();
                //删除cookies缓存
                SharedPreferences.Editor editor = ScheduleActivity.this.getSharedPreferences("cookies"
                        , MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                //判断是否要手动打开登录的activity
                if (mFlag) {
                    finish();
                } else {
                    Intent intent = new Intent(ScheduleActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        dialog.show();
    }

    /**
     * 通过列表选择对话框选择周次
     */
    private void chooseWeeks() {
        String items[] = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周", "第十周", "第十一周"
                , "第十二周", "第十三周", "第十四周", "第十五周", "第十六周", "第十七周", "第十八周", "第十九周", "第二十周"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(ScheduleActivity.this);
        dialog.setTitle("选择周次");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTableModelList.clear();
                String week = Integer.toString(which + 1);
                Log.e("mAllTableModelList", "" + mAllTableModelList.size());
                removeAllCourse();
                for (TableModel tableModel : mAllTableModelList) {
                    Log.e("table", "" + tableModel.getName() + "week:" + tableModel.getWeek() + "weekday" + tableModel.getWeekday());
                    for (String str : tableModel.getWeek()) {
                        Log.e("str", str);
                        if (str.equals(week)) {
                            mTableModelList.add(tableModel);
                            createCourseView(tableModel);
                            break;
                        }
                    }
                }
            }
        });
        dialog.show();
    }

    /**
     * 把显示出来的课程全部清除，方便选择星期后添加新的课程
     */
    private void removeAllCourse() {
        RelativeLayout monday = findViewById(R.id.monday);
        monday.removeAllViews();
        RelativeLayout tuesday = findViewById(R.id.tuesday);
        tuesday.removeAllViews();
        RelativeLayout wednesday = findViewById(R.id.wednesday);
        wednesday.removeAllViews();
        RelativeLayout thursday = findViewById(R.id.thursday);
        thursday.removeAllViews();
        RelativeLayout friday = findViewById(R.id.friday);
        friday.removeAllViews();
        RelativeLayout saturday = findViewById(R.id.saturday);
        saturday.removeAllViews();
        RelativeLayout sunday = findViewById(R.id.sunday);
        sunday.removeAllViews();
    }

    /**
     * 开启课程表activity界面
     *
     * @param context 上下文
     * @param json    需要解析的json课表数据
     * @param flag    是否经过登录
     */
    public static void actionStart(Context context, String json, boolean flag) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        intent.putExtra("Json", json);
        intent.putExtra("flag", flag);
        context.startActivity(intent);
    }
}
