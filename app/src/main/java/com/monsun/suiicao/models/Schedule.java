package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class Schedule {
    @SerializedName("CourseID")
    @Expose
    private String courseID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Day_start")
    @Expose
    private String dayStart;
    @SerializedName("Day_end")
    @Expose
    private String dayEnd;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("dayinweek")
    @Expose
    private String dayinweek;
    @SerializedName("location")
    @Expose
    private String location;
    private Calendar date;

    private boolean isexpandable = true;

    public boolean isIsexpandable() {
        return isexpandable;
    }

    public void setIsexpandable(boolean isexpandable) {
        this.isexpandable = isexpandable;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getDayinweek() {
        return dayinweek;
    }

    public void setDayinweek(String dayinweek) {
        this.dayinweek = dayinweek;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
