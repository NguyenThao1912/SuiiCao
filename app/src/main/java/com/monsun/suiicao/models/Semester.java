package com.monsun.suiicao.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Semester {

    @SerializedName("SemesterKey")
    @Expose
    private String semesterKey;
    @SerializedName("SemesterName")
    @Expose
    private String semesterName;

    public String getSemesterKey() {
        return semesterKey;
    }

    public void setSemesterKey(String semesterKey) {
        this.semesterKey = semesterKey;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Semester(String semesterKey, String semesterName) {
        this.semesterKey = semesterKey;
        this.semesterName = semesterName;
    }

    @NonNull
    @Override
    public String toString() {
        return getSemesterName();
    }
}
