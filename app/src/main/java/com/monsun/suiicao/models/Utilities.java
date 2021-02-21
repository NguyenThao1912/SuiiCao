package com.monsun.suiicao.models;

public class Utilities {
    private String utiIcon;
    private String utiName;

    public Utilities(String utiIcon, String utiName) {
        this.utiIcon = utiIcon;
        this.utiName = utiName;
    }

    public void setUtiIcon(String utiIcon) {
        this.utiIcon = utiIcon;
    }

    public String getUtiName() {
        return utiName;
    }

    public void setUtiName(String utiName) {
        this.utiName = utiName;
    }

    public String getUtiIcon() {
        return utiIcon;
    }

}
