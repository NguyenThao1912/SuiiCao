package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mentor {
    @SerializedName("mentor_id")
    @Expose
    private String mentorId;
    @SerializedName("mentor_name")
    @Expose
    private String mentorName;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("Accountid")
    @Expose
    private Integer accountid;
    @SerializedName("edu_background")
    @Expose
    private String eduBackground;
    @SerializedName("teachingtime")
    @Expose
    private String teachingtime;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("img")
    @Expose
    private String img;

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getEduBackground() {
        return eduBackground;
    }

    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }

    public String getTeachingtime() {
        return teachingtime;
    }

    public void setTeachingtime(String teachingtime) {
        this.teachingtime = teachingtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
