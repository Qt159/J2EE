package com.tuan.dto;

public class CourseSearchRequestDTO {

    private String keyword;

    public CourseSearchRequestDTO() {
    }

    public CourseSearchRequestDTO(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}