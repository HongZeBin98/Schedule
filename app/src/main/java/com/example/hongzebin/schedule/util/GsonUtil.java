package com.example.hongzebin.schedule.util;

import android.util.Log;

import com.example.hongzebin.schedule.bean.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类是用Gson来解析数据
 * Created By Mr.Bean
 */
public class GsonUtil {
    /**
     * 把课程表的json数据通过Gson解析成每一节课的相关对象
     * @param jsonData 将要解析的课程便Json数据
     * @return 每一节课的相关对象
     */
    public List<Course> parseJSONWithGson(String jsonData){
        Gson gson = new Gson();
        List<Course> courseList = gson.fromJson(jsonData, new TypeToken<List<Course>>(){}.getType());
        return addId(courseList);
    }

    /**
     * 给课程对象添加一个主键（Id），该主键是由课程的数字编码加上上课的星期构成。并且把没有老师的课程过滤掉。
     * @param list 需要添加的课程列表
     * @return  添加了主键的课程列表
     */
    private List<Course> addId(List<Course> list){
       List<Course> courseList = new ArrayList<>();
       for(int i = 0; i < list.size(); i++){
           //对课程进行过滤，把没有老师安排的课程去除掉
           Course course = list.get(i);
           if (course.getTeacher().equals("")){
               list.remove(i);
               continue;
           }
           int id = Integer.parseInt(course.getFigureNumber()) + Integer.parseInt(course.getWeekday());
           course.setId((long) id);
           courseList.add(course);
       }
       return courseList;
    }
}
