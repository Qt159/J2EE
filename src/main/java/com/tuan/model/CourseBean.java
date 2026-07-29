package com.tuan.model;

public class CourseBean {
    private String courseId;
    private String courseName;
    private int credits;
    private String lecturer;
    private int remainingSlots;
    private double tuitionFee;

    public CourseBean() {}

    public CourseBean(String courseId, String courseName, int credits, String lecturer, int remainingSlots, double tuitionFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.lecturer = lecturer;
        this.remainingSlots = remainingSlots;
        this.tuitionFee = tuitionFee;
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getLecturer() { return lecturer; }
    public void setLecturer(String lecturer) { this.lecturer = lecturer; }

    public int getRemainingSlots() { return remainingSlots; }
    public void setRemainingSlots(int remainingSlots) { this.remainingSlots = remainingSlots; }

    public double getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(double tuitionFee) { this.tuitionFee = tuitionFee; }
}