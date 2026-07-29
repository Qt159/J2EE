package com.tuan.controller;

import com.tuan.model.CourseBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tuan.model.RegistrationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/export")
public class ExportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        RegistrationBean  registration = (RegistrationBean) session.getAttribute("registration");
        if (registration == null) {
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
            return;
        }
        if (registration.getStudent() == null) {
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
            return;
        }

        String fileName = "DKHP_"+registration.getStudent().getMssv();
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".txt");
        PrintWriter out = response.getWriter();
        // Format ngày
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        out.println("Phiếu đăng ký học phần");
        out.println("Mã đăng ký: "+ registration.getRegistrationCode());
        out.println("Ngày xác nhận: " + (registration.getConfirmationDate() != null
                                ? date.format(registration.getConfirmationDate())
                                : "Chưa xác nhận"));
        out.println();
        out.println("Thông tin sinh viên");
        out.println("MSSV       : " + registration.getStudent().getMssv());
        out.println("Họ và tên: : " + registration.getStudent().getFullName());
        out.println("Email:     : " + registration.getStudent().getEmail());
        out.println("Ngành:     : " + registration.getStudent().getMajor());
        out.println();
        out.println("Danh sách học phần");
        List<CourseBean> courses = registration.getSelectedCourses();
        if(courses != null) {
            for (CourseBean course : courses) {
                out.println("Mã học phần     : " + course.getCourseId());
                out.println("Tên học phần    : " + course.getCourseName());
                out.println("Số tín chỉ      : " + course.getCredits());
                out.println("Học phí         : " + String.format("%,.0f",course.getTuitionFee())+" VNĐ");
                out.println();
            }
        }
            out.println();
            out.println("Tổng số học phần       : " + registration.getTotalCoursesCount());
            out.println("Tổng số tín chỉ        : " + registration.getTotalCredits());
            out.println("Tổng học phí           : " + String.format("%,.0f",registration.getTotalTuitionFee()) + " VNĐ");
            out.println();
            out.println("Cảm ơn bạn đã sử dụng hệ thống đăng ký học phần.");
            out.flush();
    }

}

