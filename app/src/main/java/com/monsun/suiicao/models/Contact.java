package com.monsun.suiicao.models;

public class Contact {
    private String contact_id;
    private String contact_name;
    private String contact_img;
    private String contact_status;
    private String uid;

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_img() {
        return contact_img;
    }

    public void setContact_img(String contact_img) {
        this.contact_img = contact_img;
    }

    public String getContact_status() {
        return contact_status;
    }

    public void setContact_status(String contact_status) {
        this.contact_status = contact_status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Contact(String contact_id, String contact_name, String contact_img, String contact_status, String uid) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_img = contact_img;
        this.contact_status = contact_status;
        this.uid = uid;
    }
    public Contact()
    {

    }



}
