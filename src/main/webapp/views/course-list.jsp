<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Danh sách học phần" scope="request" />
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>

<div class="container">
    <h2>Danh Sách Học Phần Cung Cấp</h2>
    <!-- Form tra cứu -->
    <form action="${pageContext.request.contextPath}/courses" method="GET" style="display: flex; gap: 10px; margin-bottom: 20px;">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" value="${keyword}" placeholder="Nhập mã hoặc tên học phần cần tra cứu..." style="max-width: 400px;">
        <button type="submit" class="btn btn-secondary">Tra cứu</button>
        <c:if test="${not empty keyword}">
            <a href="${pageContext.request.contextPath}/courses" class="btn btn-secondary" style="text-decoration: none; line-height: 24px;">Xem tất cả</a>
        </c:if>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>Mã HP</th>
            <th>Tên học phần</th>
            <th>Số tín chỉ</th>
            <th>Giảng viên</th>
            <th>Còn lại</th>
            <th>Học phí (VNĐ)</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${courseList}">
            <tr>
                <td><strong>${c.courseId}</strong></td>
                <td>${c.courseName}</td>
                <td>${c.credits}</td>
                <td>${c.lecturer}</td>
                <td>${c.remainingSlots}</td>
                <td><fmt:formatNumber value="${c.tuitionFee}" type="number" groupingUsed="true"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/cart?action=add&courseId=${c.courseId}" class="btn btn-primary" style="height: 32px; padding: 0 12px; font-size: 13px;">Thêm</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty courseList}">
            <tr>
                <td colspan="7" style="text-align: center; color: var(--text-muted);">Không tìm thấy học phần nào phù hợp.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

<%@ include file="../includes/footer.jsp" %>