package com.example.hongzebin.schedule.model;

import com.example.hongzebin.schedule.bean.Course;
import com.example.hongzebin.schedule.greendao.GreenDaoManager;

import java.util.List;

/**
 * ScheduleActivity对数据进行获取和保存数据
 * Created By Mr.Bean
 */
public class ScheduleModel {

    public interface GreenDaoCallBack{
        void onFinish(List<Course> list);
    }

    /**
     * 获取数据库中所有的课程
     * @param callBack 接口
     */
    public void getData(GreenDaoCallBack callBack){
        GreenDaoManager greenDaoManager = GreenDaoManager.Holder.getInstance();
        callBack.onFinish(greenDaoManager.queryAll());
    }

    /**
     * 把一整个课程列表保存进数据库
     * @param list 需要保存的课程列表
     */
    public void saveData(List<Course> list){
        GreenDaoManager greenDaoManager = GreenDaoManager.Holder.getInstance();
        greenDaoManager.addAll(list);
    }
}
