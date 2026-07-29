package com.tuan.controller;

import com.tuan.dto.CartRequestDTO;
import com.tuan.error.ErrorMessages;
import com.tuan.model.CourseBean;
import com.tuan.service.CourseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final CourseService courseService = new CourseService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        List<CourseBean> cart = (List<CourseBean>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        String action = request.getParameter("action");
        CartRequestDTO dto = new CartRequestDTO(request.getParameter("courseId"));
        if (action != null) {
            switch (action) {
                case "add":
                    CourseBean course = courseService.getCourseById(dto.getCourseId());
                    if (course != null) {
                        if (!cart.contains(course)) {
                            cart.add(course);
                        } else {
                            request.setAttribute("error", ErrorMessages.COURSE_ALREADY_EXISTS);
                        }
                    }
                    break;
                case "remove":
                    CourseBean removeCourse = courseService.getCourseById(dto.getCourseId());
                    if (removeCourse != null) {
                        cart.remove(removeCourse);
                    }
                    break;
                case "clear":
                    cart.clear();
                    break;
            }
        }
        int totalCredits = 0;
        double totalFee = 0;
        for (CourseBean c : cart) {
            totalCredits += c.getCredits();
            totalFee += c.getTuitionFee();
        }
        // số lần truy cập cart
        Integer visitCount = (Integer) session.getAttribute("cartVisit");
        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }
        session.setAttribute("cartVisit", visitCount);
        // gửi dữ liệu sang JSP
        request.setAttribute("cart", cart);
        request.setAttribute("totalCredits", totalCredits);
        request.setAttribute("totalFee", totalFee);
        request.setAttribute("sessionId", session.getId());
        request.setAttribute("creationTime", session.getCreationTime());
        request.setAttribute("visitCount", visitCount);
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}