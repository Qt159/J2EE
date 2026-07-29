<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Lỗi hệ thống" scope="request" />
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>

<div class="container" style="text-align: center; padding: 40px 20px;">
    <h2 style="color: var(--error); border-bottom: none;">Đã xảy ra lỗi</h2>
    <div class="alert alert-danger" style="display: inline-block; max-width: 600px; text-align: left; margin: 20px 0;">
        ${not empty error ? error : 'Yêu cầu không hợp lệ hoặc dữ liệu không tồn tại trong hệ thống.'}
    </div>
    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">Quay về Trang chủ</a>
    </div>
</div>

<%@ include file="../includes/footer.jsp" %>