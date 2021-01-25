package com.monsun.suiicao.models;

import android.text.TextUtils;

public class User {
    private String username;
    private String password;
    private int roleId;

    public User(String username, String password, int roleId) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
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
