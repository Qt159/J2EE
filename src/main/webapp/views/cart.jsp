<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Giỏ đăng ký" scope="request" />
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>

<div class="container">
    <h2>Giỏ Đăng Ký Học Phần</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>Mã HP</th>
            <th>Tên học phần</th>
            <th>Tín chỉ</th>
            <th>Học phí (VNĐ)</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${cart}">
            <tr>
                <td><strong>${c.courseId}</strong></td>
                <td>${c.courseName}</td>
                <td>${c.credits}</td>
                <td><fmt:formatNumber value="${c.tuitionFee}" type="number" groupingUsed="true"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/cart?action=remove&courseId=${c.courseId}" class="btn btn-danger" style="height: 30px; padding: 0 10px; font-size: 13px;" onclick="return confirm('Xóa học phần ${c.courseName}?');">
                        Xóa</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty cart}">
            <tr>
                <td colspan="5" style="text-align: center; color: var(--text-muted);">Giỏ đăng ký hiện đang trống.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <div class="cart-summary">
        <div>
            <p><strong>Tổng số tín chỉ:</strong> ${totalCredits}</p>
            <p><strong>Tổng học phí dự kiến:</strong> <fmt:formatNumber value="${totalFee}" type="number" groupingUsed="true"/> VNĐ</p>
        </div>
        <div style="display: flex; gap: 10px;">
            <a href="${pageContext.request.contextPath}/cart?action=clear" class="btn btn-danger">Làm trống giỏ</a>
            <c:if test="${not empty cart and cart.size() > 0}">
                <a href="${pageContext.request.contextPath}/confirm" class="btn btn-success">Xác nhận đăng ký</a>
            </c:if>
        </div>
    </div>

    <div class="session-info">
        <p><strong>Thông tin kỹ thuật Session:</strong></p>
        <p>Session ID: ${sessionId} | Lượt truy cập giỏ: ${visitCount} lần</p>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>