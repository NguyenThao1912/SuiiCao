package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthplace")
    @Expose
    private String birthplace;
    @SerializedName("year_in")
    @Expose
    private String yearIn;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("student_mobile")
    @Expose
    private String studentMobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Accountid")
    @Expose
    private Integer accountid;
    @SerializedName("Evaluation")
    @Expose
    private String evaluation;
    @SerializedName("images")
    @Expose
    private String img;
    @SerializedName("parent_name")
    @Expose
    private String parentname;

    @SerializedName("CCCD")
    @Expose
    private String CCCD;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("religion")
    @Expose
    private String religion;

    @SerializedName("nation")
    @Expose
    private String nation;
    private boolean checkstatus = false;

    public boolean isCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(boolean checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    private String username;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFullName()
    {
        return lastName + " " + firstName;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getYearIn() {
        return yearIn;
    }

    public void setYearIn(String yearIn) {
        this.yearIn = yearIn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
