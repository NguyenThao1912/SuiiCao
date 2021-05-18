package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result{
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("study_count")
    @Expose
    private String studyCount;
    @SerializedName("course_credit")
    @Expose
    private String courseCredit;
    @SerializedName("mark_progress")
    @Expose
    private String markProgress;
    @SerializedName("mark_exam")
    @Expose
    private String markExam;
    @SerializedName("mark_total")
    @Expose
    private String markTotal;
    @SerializedName("evaluate")
    @Expose
    private Object evaluate;

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

    public String getStudyCount() {
        return studyCount;
    }

    public void setStudyCount(String studyCount) {
        this.studyCount = studyCount;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getMarkProgress() {
        return markProgress;
    }

    public void setMarkProgress(String markProgress) {
        this.markProgress = markProgress;
    }

    public String getMarkExam() {
        return markExam;
    }

    public void setMarkExam(String markExam) {
        this.markExam = markExam;
    }

    public String getMarkTotal() {
        return markTotal;
    }

    public void setMarkTotal(String markTotal) {
        this.markTotal = markTotal;
    }

    public Object getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Object evaluate) {
        this.evaluate = evaluate;
    }
}
