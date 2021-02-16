package com.monsun.suiicao.models;

public class User {
    private String username;
    private String password;
    private int roleId;
    private String userid;
    private String FirstName;
    private String LastName;
    private String major;
    private String Address;

    public String getFirstName() {
        return FirstName;
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
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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


    public User(String username, String password, int roleId,String userid) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.userid = userid;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public int getRoleId() { return roleId; }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
