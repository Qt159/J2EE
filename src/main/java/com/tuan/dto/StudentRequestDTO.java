package com.tuan.dto;

public class StudentRequestDTO {

    private String mssv;
    private String fullName;
    private String email;
    private String phone;
    private String major;
    private String enrollmentPeriod;
    private String note;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String mssv, String fullName, String email,
                             String phone, String major,
                             String enrollmentPeriod, String note) {
        this.mssv = mssv;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.major = major;
        this.enrollmentPeriod = enrollmentPeriod;
        this.note = note;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEnrollmentPeriod() {
        return enrollmentPeriod;
    }

    public void setEnrollmentPeriod(String enrollmentPeriod) {
        this.enrollmentPeriod = enrollmentPeriod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}