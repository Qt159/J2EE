<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Kết quả tìm kiếm học phần" scope="request" />
<%@ include file="/includes/header.jsp" %>
<%@ include file="/includes/menu.jsp" %>

<main>
    <div class="container">
        <h2>Kết quả tìm kiếm học phần</h2>
        <p>Từ khóa tìm kiếm: <strong>${keyword}</strong></p>

        <c:if test="${empty courseList}">
            <div class="alert alert-danger">Không tìm thấy học phần nào phù hợp với từ khóa "${keyword}".</div>
        </c:if>

        <c:if test="${not empty courseList}">
            <table>
                <thead>
                <tr>
                    <th>Mã HP</th>
                    <th>Tên học phần</th>
                    <th>Số tín chỉ</th>
                    <th>Học phí</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="course" items="${courseList}">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.courseName}</td>
                        <td>${course.credits}</td>
                        <td>${String.format("%,.0f", course.tuitionFee)} VNĐ</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart?action=add&courseId=${course.courseId}" class="btn btn-primary" style="height: 32px; padding: 0 12px; font-size: 13px;">Đăng ký</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div style="margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/courses" class="btn btn-secondary">Quay lại danh sách học phần</a>
        </div>
    </div>
</main>

<%@ include file="/includes/footer.jsp" %>