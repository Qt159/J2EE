package com.tuan.controller;

import com.tuan.error.ErrorMessages;
import com.tuan.model.CourseBean;
import com.tuan.model.RegistrationBean;
import com.tuan.model.StudentBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.tuan.service.CourseService.courseList;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {
    private static int registrationCode = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        RegistrationBean old = (RegistrationBean) session.getAttribute("registration");
        if (old != null) {
            response.sendRedirect(request.getContextPath() + "/views/confirmation.jsp");
            return;
        }
        StudentBean student = (StudentBean) session.getAttribute("student");
        List<CourseBean> selectedCourses = (List<CourseBean>) session.getAttribute("cart");
        if (student == null) {
            request.setAttribute("error", ErrorMessages.STUDENT_NOT_FOUND);
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            return;
        }
        if (selectedCourses == null || selectedCourses.isEmpty()) {
            request.setAttribute("error", ErrorMessages.CART_EMPTY);
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            return;
        }
        String registrationCode = createRegistrationCode();
        RegistrationBean registration = new RegistrationBean(registrationCode, new Date(),
                student, selectedCourses);
        session.setAttribute("registration", registration);// cho export
        // gửi sang JSP
        request.setAttribute("registration", registration);
        for(CourseBean selected : selectedCourses){
            for(CourseBean course: courseList){
                if(selected.getCourseId().equals(course.getCourseId())){
                    course.setRemainingSlots(course.getRemainingSlots() - 1);
                }

            }
        }
        request.getRequestDispatcher("/views/confirmation.jsp").forward(request, response);
    }

    private String createRegistrationCode() {
        return String.valueOf(registrationCode++);
    }
}