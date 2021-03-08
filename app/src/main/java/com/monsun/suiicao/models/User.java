package com.monsun.suiicao.models;

public class User {
    private String username;
    private String password;
    private String userid;
    private String FirstName;
    private String LastName;
    private String major;
    private String gender;
    private String dateofbirth;
    private String classid;
    private String email;
    private String address;
    private String k;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullName()
    {
        return LastName + " " + FirstName;
    }
    public String getmajor_class_year()
    {
        switch (major)
        {
            case "IT":
                return "Công nghệ thông tin " + classid + " K" + k;
            case "Eco":
                return "Kinh tế Vận Tải " + classid + " K" + k;
            default:
                return "Error";
        }
    }

    public User(String username, String password,String userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
