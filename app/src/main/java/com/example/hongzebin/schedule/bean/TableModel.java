package com.example.hongzebin.schedule.bean;

import java.util.List;

public class TableModel {

    private String name;    //课程名称
    private String number;    //课程编码
    private String major;   //需要上课的专业班级
    private String figureNumber;    //数字编号
    private List<String> time;   //上课时间
    private List<String> week; //上课周次
    private String weekday;  //需要上课的星期
    private String place; //上课地点
    private String teacher;  //任课老师

    public TableModel(String name, String number, String major, String figureNumber, List<String> time,
                      List<String> week, String weekday, String place, String teacher) {
        this.name = name;
        this.number = number;
        this.major = major;
        this.figureNumber = figureNumber;
        this.time = time;
        this.week = week;
        this.weekday = weekday;
        this.place = place;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getMajor() {
        return major;
    }

    public String getFigureNumber() {
        return figureNumber;
    }

    public List<String> getTime() {
        return time;
    }

    public List<String> getWeek() {
        return week;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getPlace() {
        return place;
    }

    public String getTeacher() {
        return teacher;
    }
}
