package com.example.hongzebin.schedule.greendao;

import android.app.Application;
import android.util.Log;

import com.example.hongzebin.schedule.bean.Course;
import com.example.hongzebin.schedule.util.ScheduleApplication;

import java.util.List;

public class GreenDaoManager {

     private DaoMaster.DevOpenHelper devOpenHelper;
     private DaoMaster daoMaster;
     private DaoSession daoSession;
     private CourseDao courseDao;

    private GreenDaoManager(){
        Log.e("context", "" + ScheduleApplication.getmContext());
        devOpenHelper = new DaoMaster.DevOpenHelper(ScheduleApplication.getmContext()
                , "Courses.db", null);
        boolean flag = devOpenHelper == null;
        Log.e("flag", "" + flag);
        boolean flag1 = devOpenHelper.getWritableDatabase() == null;

        Log.e("flag1", "" + flag1);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        courseDao = daoSession.getCourseDao();
    }

    public static class Holder{
        private static GreenDaoManager greenDaoManager = new GreenDaoManager();
        public static GreenDaoManager getInstance(){
            return greenDaoManager;
        }
    }

    public void addCourse(Course course){
        courseDao.insert(course);
    }

    public void addAll(List<Course> list){
        for(Course course: list){
            courseDao.insert(course);
        }
    }

    public void deleteAll(){
        courseDao.deleteAll();
    }

    public List<Course> queryAll(){
        return courseDao.queryBuilder().list();
    }
}
