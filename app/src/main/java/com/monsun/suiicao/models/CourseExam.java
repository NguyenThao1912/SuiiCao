package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseExam {

    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_credit")
    @Expose
    private String courseCredit;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Shift")
    @Expose
    private String shift;
    @SerializedName("Format")
    @Expose
    private String format;
    @SerializedName("SBD")
    @Expose
    private String sBD;
    @SerializedName("Location")
    @Expose
    private String location;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getDate() {
        return date.substring(0,10);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSBD() {
        return sBD;
    }

    public void setSBD(String sBD) {
        this.sBD = sBD;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
