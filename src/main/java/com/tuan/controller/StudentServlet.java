package com.tuan.controller;

import com.tuan.error.ErrorMessages;
import com.tuan.dto.StudentRequestDTO;
import com.tuan.mapper.StudentMapper;
import com.tuan.model.StudentBean;
import com.tuan.util.ValidationUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String theme = "light";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("mssv".equals(cookie.getName())) {
                    request.setAttribute("mssv", URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));}
                if ("major".equals(cookie.getName())) {
                    request.setAttribute("major", URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));}
                if ("theme".equals(cookie.getName())) {
                    request.setAttribute("theme", URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));}
            }
        }
        request.setAttribute("theme", theme);
        request.getRequestDispatcher("/views/student-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setMssv(request.getParameter("mssv"));
        dto.setFullName(request.getParameter("fullName"));
        dto.setEmail(request.getParameter("email"));
        dto.setPhone(request.getParameter("phone"));
        dto.setMajor(request.getParameter("major"));
        dto.setEnrollmentPeriod(request.getParameter("enrollmentPeriod"));
        dto.setNote(request.getParameter("note"));

        Map<String, String> errors = new HashMap<>();

        if (ValidationUtils.isEmpty(dto.getMssv())) {
            errors.put("mssv", ErrorMessages.REQUIRED_MSSV);

        } else if (!ValidationUtils.isValidMSSV(dto.getMssv())) {
            errors.put("mssv", ErrorMessages.INVALID_MSSV);
        }

        if (ValidationUtils.isEmpty(dto.getFullName())) {
            errors.put("fullName", ErrorMessages.REQUIRED_FULL_NAME);}

        if (ValidationUtils.isEmpty(dto.getEmail())) {
            errors.put("email", ErrorMessages.REQUIRED_EMAIL);

        } else if (!ValidationUtils.isValidEmail(dto.getEmail())) {
            errors.put("email", ErrorMessages.INVALID_EMAIL);
        }
        if (errors.isEmpty()==false) {
            request.setAttribute("errors", errors);
            request.setAttribute("student", dto);
            request.getRequestDispatcher("/views/student-form.jsp")
                    .forward(request, response);
            return;
        }
        //DTO -> Bean
        StudentBean student = StudentMapper.toBean(dto);
        HttpSession session = request.getSession();
        session.setAttribute("student", student);
        Cookie mssvCookie = new Cookie("mssv", URLEncoder.encode(student.getMssv(), StandardCharsets.UTF_8));
        mssvCookie.setMaxAge(COOKIE_MAX_AGE);
        mssvCookie.setPath("/");
        response.addCookie(mssvCookie);
        Cookie majorCookie = new Cookie("major", URLEncoder.encode(student.getMajor(), StandardCharsets.UTF_8));
        majorCookie.setMaxAge(COOKIE_MAX_AGE);
        majorCookie.setPath("/");
        response.addCookie(majorCookie);
        String theme = request.getParameter("theme");
        if (theme == null || theme.isEmpty()) {
            theme = "light";
        }
        if (theme != null && !theme.isEmpty()) {
            Cookie themeCookie = new Cookie("theme", URLEncoder.encode(theme, StandardCharsets.UTF_8));
            themeCookie.setMaxAge(COOKIE_MAX_AGE);
            themeCookie.setPath("/");
            response.addCookie(themeCookie);
        }
        response.sendRedirect(request.getContextPath() + "/courses");
    }
}