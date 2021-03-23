package com.monsun.suiicao.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentExam {
        @SerializedName("STT")
        @Expose
        private String stt;
        @SerializedName("CourseID")
        @Expose
        private String courseID;
        @SerializedName("CourseName")
        @Expose
        private String courseName;
        @SerializedName("CourseCredit")
        @Expose
        private String courseCredit;
        @SerializedName("ExamDay")
        @Expose
        private String examDay;
        @SerializedName("ShiftExam")
        @Expose
        private String shiftExam;
        @SerializedName("FormatExam")
        @Expose
        private String formatExam;
        @SerializedName("StuNumber")
        @Expose
        private String stuNumber;
        @SerializedName("RoomExam")
        @Expose
        private String roomExam;
        @SerializedName("Note")
        @Expose
        private String note;

        public String getSTT() {
            return stt;
        }

        public void setSTT(String sTT) {
            this.stt = sTT;
        }

        public String getCourseID() {
            return courseID;
        }

        public void setCourseID(String courseID) {
            this.courseID = courseID;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseCredit() {
            return courseCredit;
        }

        public void setCourseCredit(String courseCredit) {
            this.courseCredit = courseCredit;
        }

        public String getExamDay() {
            return examDay;
        }

        public void setExamDay(String examDay) {
            this.examDay = examDay;
        }

        public String getShiftExam() {
            return shiftExam;
        }

        public void setShiftExam(String shiftExam) {
            this.shiftExam = shiftExam;
        }

        public String getFormatExam() {
            return formatExam;
        }

        public void setFormatExam(String formatExam) {
            this.formatExam = formatExam;
        }

        public String getStuNumber() {
            return stuNumber;
        }

        public void setStuNumber(String stuNumber) {
            this.stuNumber = stuNumber;
        }

        public String getRoomExam() {
            return roomExam;
        }

        public void setRoomExam(String roomExam) {
            this.roomExam = roomExam;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
}
