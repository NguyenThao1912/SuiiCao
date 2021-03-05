package com.monsun.suiicao.models;

public class Utilities {
    private int utiIcon;
    private String utiName;

    public Utilities(int utiIcon, String utiName) {
        this.utiIcon = utiIcon;
        this.utiName = utiName;
    }

    public void setUtiIcon(int utiIcon) {
        this.utiIcon = utiIcon;
    }

    public String getUtiName() {
        return utiName;
    }

    public void setUtiName(String utiName) {
        this.utiName = utiName;
    }

    public int getUtiIcon() {
        return utiIcon;
    }

}
