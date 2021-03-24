package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Curriculum {
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_credit")
    @Expose
    private String courseCredit;
    @SerializedName("course_theory")
    @Expose
    private String courseTheory;
    @SerializedName("course_practical")
    @Expose
    private String coursePractical;
    @SerializedName("discuss")
    @Expose
    private String discuss;
    @SerializedName("semester")
    @Expose
    private String semester;

    private boolean isexpandable = false;

    public boolean isIsexpandable() {
        return isexpandable;
    }

    public void setIsexpandable(boolean isexpandable) {
        this.isexpandable = isexpandable;
    }

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

    public String getCourseTheory() {
        return courseTheory;
    }

    public void setCourseTheory(String courseTheory) {
        this.courseTheory = courseTheory;
    }

    public String getCoursePractical() {
        return coursePractical;
    }

    public void setCoursePractical(String coursePractical) {
        this.coursePractical = coursePractical;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
