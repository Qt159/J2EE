package com.tuan.service;

import com.tuan.model.CourseBean;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    public static final List<CourseBean> courseList = new ArrayList<>();
    static {
        courseList.add(new CourseBean("MTH10334", "Lập trình Web với J2EE", 4, "Thầy Võ Đức Cẩm Hải", 30, 4000000));
        courseList.add(new CourseBean("MTH10360", "Lập trình Java", 4, "Thầy Hà Văn Thảo", 30, 4000000));
        courseList.add(new CourseBean("MTH10323", "Xử lý đa chiều", 4, "Thầy A", 30, 4000000));
        courseList.add(new CourseBean("MTH10356", "Trí tuệ nhân tạo nâng cao", 4, "Thầy B", 30, 4000000));
        courseList.add(new CourseBean("MTH10605", "Python cho khoa học dữ liệu", 3, "Cô C", 30, 3000000));
        courseList.add(new CourseBean("MTH10624", "Phân tích mạng xã hội", 3, "Thầy D", 30, 3000000));
    }

    public List<CourseBean> searchCourses(String keyword) {
        List<CourseBean> result = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }
        keyword = keyword.trim().toLowerCase();
        for (CourseBean course : courseList) {
            if (course.getCourseId().toLowerCase().contains(keyword) || course.getCourseName().toLowerCase().contains(keyword)) {
                result.add(course);
            }
        }
        return result;
    }
    public CourseBean getCourseById(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) {
            return null;
        }
        for (CourseBean course : courseList) {
            if (course.getCourseId().equalsIgnoreCase(courseId.trim())) {
                return course;
            }
        }
        return null;
    }
    public List<CourseBean> getAllCourses() {
        return courseList;
    }
}