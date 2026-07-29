package com.tuan.controller;

import com.tuan.dto.CourseSearchRequestDTO;
import com.tuan.model.CourseBean;
import com.tuan.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private final CourseService courseService = new CourseService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // Không có action thì show all courses
        if (action == null || action.isEmpty()) {
            List<CourseBean> courses = courseService.getAllCourses();
            request.setAttribute("courseList", courses);
            request.getRequestDispatcher("/views/course-list.jsp").forward(request, response);
            return;
        }

        if ("search".equals(action)) {
            CourseSearchRequestDTO dto = new CourseSearchRequestDTO(request.getParameter("keyword"));
            String keyword = dto.getKeyword();
            List<CourseBean> result = courseService.searchCourses(keyword);
            request.setAttribute("courseList", result);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("/views/search-result.jsp").forward(request, response);
        }
    }
}