package com.example.hongzebin.schedule.util;

import com.example.hongzebin.schedule.bean.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    public static List<Course> parseJSONWithGson(String jsonData){
        Gson gson = new Gson();
        List<Course> courseList = gson.fromJson(jsonData, new TypeToken<List<Course>>(){}.getType());
        return addId(courseList);
    }

    private static List<Course> addId(List<Course> list){
       List<Course> courseList = new ArrayList<>();
       for(Course course: list){
           int id = Integer.parseInt(course.getFigureNumber()) + Integer.parseInt(course.getWeekday());
           course.setId((long) id);
           courseList.add(course);
       }
       return courseList;
    }
}
