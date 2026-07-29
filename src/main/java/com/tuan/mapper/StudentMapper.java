package com.tuan.mapper;

import com.tuan.dto.StudentRequestDTO;
import com.tuan.model.StudentBean;

public class StudentMapper {
    private StudentMapper() {}
    public static StudentBean toBean(StudentRequestDTO dto) {
        if (dto == null) {
            return null;}
        return new StudentBean(
                dto.getMssv(), dto.getFullName(), dto.getEmail(),
                dto.getPhone(), dto.getMajor(), dto.getEnrollmentPeriod(), dto.getNote());
    }
}