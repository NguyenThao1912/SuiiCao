package com.monsun.suiicao.models;

import android.text.TextUtils;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public boolean isPasswordGreaterThan_Eight()
    {
        return password.length() >= 8;
    }
    public boolean isUsernameEmpty()
    {
        return TextUtils.isEmpty(username);
    }
    public boolean isPasswordEmpty()
    {
        return TextUtils.isEmpty(password);
    }
}
