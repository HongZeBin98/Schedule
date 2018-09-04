package com.example.hongzebin.schedule.greendao;

import android.app.Application;
import android.util.Log;

import com.example.hongzebin.schedule.bean.Course;
import com.example.hongzebin.schedule.util.ScheduleApplication;

import java.util.List;

/**
 * 该类用于对数据库进行增删查的操作，该类采用了单例
 * Created By Mr.Bean
 */
public class GreenDaoManager {

    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private CourseDao courseDao;

    private GreenDaoManager() {
        devOpenHelper = new DaoMaster.DevOpenHelper(ScheduleApplication.getmContext()
                , "Courses.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        courseDao = daoSession.getCourseDao();
    }

    /**
     * 运用静态内部类来实现单例
     */
    public static class Holder {
        private static GreenDaoManager greenDaoManager = new GreenDaoManager();

        public static GreenDaoManager getInstance() {
            return greenDaoManager;
        }
    }

    /**
     * 添加课程进数据库
     * @param course 需要添加的课程
     */
    public void addCourse(Course course) {
        courseDao.insert(course);
    }

    /**
     * 把一个课程列表添加进数据库
     * @param list 需要添加的课程列表
     */
    public void addAll(List<Course> list) {
        for (Course course : list) {
            courseDao.insert(course);
        }
    }

    /**
     * 清空数据库
     */
    public void deleteAll() {
        courseDao.deleteAll();
    }

    /**
     * 获取数据库中所有的课程
     * @return 包括所有课程的列表
     */
    public List<Course> queryAll() {
        return courseDao.queryBuilder().list();
    }
}
