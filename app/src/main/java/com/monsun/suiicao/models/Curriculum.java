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
    private Integer courseCredit;
    @SerializedName("course_theory")
    @Expose
    private Integer courseTheory;
    @SerializedName("course_practical")
    @Expose
    private Integer coursePractical;
    @SerializedName("discuss")
    @Expose
    private Integer discuss;
    @SerializedName("semester")
    @Expose
    private Integer semester;

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

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Integer getCourseTheory() {
        return courseTheory;
    }

    public void setCourseTheory(Integer courseTheory) {
        this.courseTheory = courseTheory;
    }

    public Integer getCoursePractical() {
        return coursePractical;
    }

    public void setCoursePractical(Integer coursePractical) {
        this.coursePractical = coursePractical;
    }

    public Integer getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Integer discuss) {
        this.discuss = discuss;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
