package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mentor {

    private String Username;

    @SerializedName("mentor_id")
    @Expose
    private Integer mentorId;
    @SerializedName("mentor_name")
    @Expose
    private String mentorName;
    @SerializedName("class_id")
    @Expose
    private Integer classId;
    @SerializedName("Accountid")
    @Expose
    private Integer accountid;
    @SerializedName("HocHam")
    @Expose
    private String hocHam;
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
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("HocVi")
    @Expose
    private String hocVi;

    public Integer getMentorId() {
        return mentorId;
    }

    public void setMentorId(Integer mentorId) {
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

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
