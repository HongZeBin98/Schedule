package com.example.hongzebin.schedule.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Course {
    @Id
    private Long id;

    @Property(nameInDb = "NAME")
    @SerializedName("kcmc")
    private String name;    //课程名称

    @Property(nameInDb = "NUMBER")
    @SerializedName("kcbh")
    private String number;    //课程编码

    @Property(nameInDb = "MAJOR")
    @SerializedName("jxbmc")
    private String major;   //需要上课的专业班级

    @Property(nameInDb = "FIGURENUMBER")
    @SerializedName("kcrwdm")
    private String figureNumber;    //数字编号

    @Property(nameInDb = "TIME")
    @SerializedName("jcdm2")
    private String time;   //上课时间

    @Property(nameInDb = "WEEK")
    @SerializedName("zcs")
    private String week; //上课周次

    @Property(nameInDb = "WEEKDAY")
    @SerializedName("xq")
    private String weekday;  //需要上课的星期

    @Property(nameInDb = "PLACE")
    @SerializedName("jxcdmcs")
    private String place; //上课地点

    @Property(nameInDb = "TEACHER")
    @SerializedName("teaxms")
    private String teacher;  //任课老师

    @Generated(hash = 1355838961)
    public Course() {
    }

    @Generated(hash = 2006004045)
    public Course(Long id, String name, String number, String major,
            String figureNumber, String time, String week, String weekday,
            String place, String teacher) {
        this.id = id;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFigureNumber() {
        return this.figureNumber;
    }

    public void setFigureNumber(String figureNumber) {
        this.figureNumber = figureNumber;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeekday() {
        return this.weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }


}
