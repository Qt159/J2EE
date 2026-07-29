package com.tuan.model;

import java.util.Date;
import java.util.List;

public class RegistrationBean {
    private String registrationCode;
    private Date confirmationDate;
    private StudentBean student;
    private List<CourseBean> selectedCourses;
    private int totalCredits;
    private double totalTuitionFee;

    public RegistrationBean() {}

    public RegistrationBean(String registrationCode, Date confirmationDate, StudentBean student, List<CourseBean> selectedCourses) {
        this.registrationCode = registrationCode;
        this.confirmationDate = confirmationDate;
        this.student = student;
        this.selectedCourses = selectedCourses;
        calculateTotals();
    }

    private void calculateTotals() {
        this.totalCredits = 0;
        this.totalTuitionFee = 0.0;
        if (selectedCourses != null) {
            for (CourseBean course : selectedCourses) {
                this.totalCredits += course.getCredits();
                this.totalTuitionFee += course.getTuitionFee();
            }
        }
    }
    public String getRegistrationCode() { return registrationCode; }
    public void setRegistrationCode(String registrationCode) { this.registrationCode = registrationCode; }

    public Date getConfirmationDate() { return confirmationDate; }
    public void setConfirmationDate(Date confirmationDate) { this.confirmationDate = confirmationDate; }

    public StudentBean getStudent() { return student; }
    public void setStudent(StudentBean student) { this.student = student; }

    public List<CourseBean> getSelectedCourses() { return selectedCourses; }
    public void setSelectedCourses(List<CourseBean> selectedCourses) {
        this.selectedCourses = selectedCourses;
        calculateTotals();
    }

    public int getTotalCoursesCount() {
        return (selectedCourses != null) ? selectedCourses.size() : 0;
    }

    public int getTotalCredits() { return totalCredits; }
    public double getTotalTuitionFee() { return totalTuitionFee; }
}