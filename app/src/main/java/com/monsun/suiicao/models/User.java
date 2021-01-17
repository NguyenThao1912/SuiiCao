package com.monsun.suiicao.models;

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
}
