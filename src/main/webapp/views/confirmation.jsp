<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Xác nhận đăng ký" scope="request" />
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>

<div class="container">
    <h2>Xác Nhận Đăng Ký Học Phần Thành Công</h2>

    <div class="alert alert-success">
        Hệ thống đã ghi nhận thông tin đăng ký học phần của bạn. Mã đăng ký chính thức: <strong>#${registration.registrationCode}</strong>
    </div>

    <h3>Thông tin sinh viên</h3>
    <p><strong>MSSV:</strong> ${registration.student.mssv}</p>
    <p><strong>Họ và tên:</strong> ${registration.student.fullName}</p>
    <p><strong>Email:</strong> ${registration.student.email}</p>
    <p><strong>Ngành:</strong> ${registration.student.major} | <strong>Khóa:</strong> ${registration.student.enrollmentPeriod}</p>

    <h3 style="margin-top: 24px;">Danh sách học phần đã đăng ký</h3>
    <table>
        <thead>
        <tr>
            <th>Mã HP</th>
            <th>Tên học phần</th>
            <th>Tín chỉ</th>
            <th>Học phí</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${registration.selectedCourses}">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.courseName}</td>
                <td>${c.credits}</td>
                <td><fmt:formatNumber value="${c.tuitionFee}" type="number" groupingUsed="true"/> VNĐ</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="cart-summary">
        <div>
            <p><strong>Tổng số học phần:</strong> ${registration.totalCoursesCount}</p>
            <p><strong>Tổng số tín chỉ:</strong> ${registration.totalCredits}</p>
            <p><strong>Tổng học phí:</strong> <fmt:formatNumber value="${registration.totalTuitionFee}" type="number" groupingUsed="true"/> VNĐ</p>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/export" class="btn btn-primary">Tải phiếu đăng ký (.txt)</a>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>