package com.tuan.dto;

public class CartRequestDTO {

    private String courseId;

    public CartRequestDTO() {
    }

    public CartRequestDTO(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}